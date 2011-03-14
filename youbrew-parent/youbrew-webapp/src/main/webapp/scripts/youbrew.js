/**
 *
 *	  Example Application (You Brew) demonstrating a simple CRUD grid (in front of a
 *	  pretty vanilla spring/hibernate/mysql/spring-mvc/restful/json webapp).
 *
 *   Copyright (C) 2011  Jacob Heric
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   see <http://www.gnu.org/licenses/>.
 *
 */

Ext.ns("youbrew");

// Application instance for showing user-feedback messages.
var App = new Ext.App({});

// HttpProxy instance, utilizes parameter "api" object here constructing url manually.
var proxy = new Ext.data.HttpProxy({
	api: {
		read : {url: 'recipe', method: 'GET'},
		create : {url: 'recipe', method: 'POST'},
		update: {url: 'recipe', method: 'PUT'},
		destroy: {url: 'recipe', method: 'DELETE'}
	}
});

// Recipe json reader
var reader = new Ext.data.JsonReader({
	totalProperty: 'total',
	successProperty: 'success',
	idProperty: 'id',
	root: 'items',
	messageProperty: 'message'
}, [
	{name: 'id'},
	{name: 'name', allowBlank: false},
	{name: 'brewNotes', allowBlank: false},
	{name: 'tasteNotes', allowBlank: false},
	{name: 'yeast', allowBlank: true},
	{name: 'yeastName', allowBlank: true}
]);

// DataWriter component.
var writer = new Ext.data.JsonWriter({
	encode: false,
	writeAllFields: true,
	listful: true
});

//  Store collecting the Proxy, Reader and Writer together.
var store = new Ext.data.Store({
	id: 'recipes',
	proxy: proxy,
	reader: reader,
	writer: writer,
	autoSave: false // requires user to hit save
	//autoLoad: true
});
store.load({params:{start:0, limit:10}});


var yeastStore = new Ext.data.JsonStore({
	root: 'items',
	idProperty: 'id',
	remoteSort: true,
	restful: true,
	autoSave: false,
	proxy: new Ext.data.HttpProxy({
		url: 'recipe/yeast'
	}),
	writer: null,
	fields: [
		{name: 'id'},
		{name: 'description', type: 'string'},
		{name: 'name', type: 'string'},
		{name: 'attenuation'},
		{name: 'yeastName'}
	]
});

//
// Listen to all write events
Ext.data.DataProxy.on('write', function(proxy, action, result, res, rs) {
	App.setAlert(true, action + ':' + res.message);
});

//
// Listen to all write events
Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
	if (type === 'remote') {
		Ext.Msg.show({
			title: 'REMOTE EXCEPTION',
			msg: res.message,
			icon: Ext.MessageBox.ERROR,
			buttons: Ext.Msg.OK
		});
	}
});

//renderer needed to display correct field when not editing combo (see API)
Ext.util.Format.comboRenderer = function(combo) {
	return function(value) {
		var record = combo.findRecord(combo.valueField, value);
		return record ? record.get(combo.displayField) : combo.valueNotFoundText;
	}
}

var textField = new Ext.form.TextField();

// Grid-columns with meta-data from backend.
var recipeColumns = [
	{header: "ID", width: 40, sortable: true, dataIndex: 'id'},
	{header: "Name", width: 100, sortable: true, dataIndex: 'name', editor: textField},
	{header: "Brew Notes", width: 180, sortable: true, dataIndex: 'brewNotes', editor: textField},
	{header: "Taste Notes", width: 180, sortable: true, dataIndex: 'tasteNotes', editor: textField},
	{
		header: 'Yeast',
		width: 130,
		dataIndex: 'yeastName',
		editor: new Ext.form.ComboBox({
			editable: false,
			store: this.yeastStore,
			mode: 'remote',
			triggerAction: 'all',
			lazyRender: true,
			listClass: 'x-combo-list-small',
			displayField: 'name',
			valueField: 'name',
			hiddenName: 'yeastName'
		}),
		renderer:

			  function(value, metaData, record, rowIndex, colIndex, store) {
				  if (value) {
					  return value;
				  }
				  else {
					  var yeast = record.get('yeast');
					  return yeast ? yeast.name : '';
				  }
			  }
	}
	/* No date time picker in extjs, so leave for now.  
	 {header: "Brew Start", width: 120, sortable: true, dataIndex: 'start',  xtype:'datecolumn', format: 'M d, Y H:i:s', editor: textField},
	 {header: "Brew End", width: 120, sortable: true, dataIndex: 'end',  xtype:'datecolumn', format: 'M d, Y H:i:s', editor: textField}*/
];

Ext.onReady(function() {
	Ext.QuickTips.init();

	// create recipe.Form instance (@see recipeForm.js)
	var recipeForm = new youbrew.recipe.Form({
		renderTo: 'recipe-form',
		yeastStore: this.yeastStore,
		listeners: {
			create : function(fpanel, data) {   // <-- custom "create" event defined in youbrew.recipe.Form class
				var rec = new recipeGrid.store.recordType(data);
				recipeGrid.store.insert(0, rec);
			}
		}
	});

	// create youbrew.recipe.Grid instance (@see recipeGrid.js)
	var recipeGrid = new youbrew.recipe.Grid({
		renderTo: 'recipe-grid',
		store: store,
		clicksToEdit: 'auto',
		columns : recipeColumns,
		listeners: {
			rowclick: function(g, index, ev) {
				var rec = g.store.getAt(index);
				recipeForm.loadRecord(rec);
				//
				//Load the yeast display field
				recipeForm.yeastCombo.setRawValue(rec.get('yeast') ? rec.get('yeast').name : null);
			},
			destroy : function() {
				recipeForm.getForm().reset();
			}
		}
	});
});
