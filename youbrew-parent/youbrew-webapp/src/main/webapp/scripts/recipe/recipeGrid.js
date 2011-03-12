/**
 *
 * 	 Youbrew sample starter app recipe grid.
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
 *   See <http://www.gnu.org/licenses/>.
 *
 */
Ext.ns('youbrew', 'youbrew.recipe');
/**
 * youbrew.recipe.Grid
 * A recipe EditorGridPanel, clearly derived from the extjs examples.
 */
youbrew.recipe.Grid = Ext.extend(Ext.grid.EditorGridPanel, {
    renderTo: 'recipe-grid',
    iconCls: 'silk-grid',
    frame: true,
    title: 'YouBrew Recipe Grid',
    height: 350,
    width: 900,
    style: 'margin-top: 10px',
	filterField: null,

    initComponent : function() {

        // viewConfig
        this.viewConfig = {
            forceFit: true
        };

        // relay the Store's CRUD events into this grid so these events can be conveniently listened-to in our application-code.
        this.relayEvents(this.store, ['destroy', 'save', 'update']);

        // build toolbars and buttons.
        this.tbar = this.buildTopToolbar();
        this.bbar = this.buildBottomToolbar();
        this.buttons = this.buildUI();

        // super
        youbrew.recipe.Grid.superclass.initComponent.call(this);
    },

    /**
     * buildTopToolbar
     */
    buildTopToolbar : function() {
        return [
		{
			xtype: 'textfield',
			fieldLabel: 'Search Any Field',
			ref: '../filterField',
			name: 'query',
			emptyText: 'Search Any Field',
			allowBlank: true,
			selectOnFocus : true,
			width: 200
		},
		'-',
		{
			text: 'Apply',
			tooltip: 'Apply current search filter',
			iconCls: 'silk-accept',
			scope: this,
			handler: this.onApplyFilter
		},
		'-',
		{
			text: 'Reset',
			tooltip: 'Reset to default search filters',
			iconCls: 'silk-cancel',
			scope: this,
			handler: this.onResetFilter
		},
		{
			xtype: "tbfill"
		},
		{
            text: 'Add',
            iconCls: 'silk-add',
            handler: this.onAdd,
            scope: this
        }, '-', {
            text: 'Delete',
            iconCls: 'silk-delete',
            handler: this.onDelete,
            scope: this
        }, '-'
		,
		{
			text: 'Save',
			iconCls: 'silk-save',
			handler : this.onSave,
			scope: this
		}

		];
    },

    /**
     * buildBottomToolbar
     */
    buildBottomToolbar : function() {

		return [
			{
				xtype: 'paging',
            	pageSize: 10,				
				store : this.store,
				displayInfo : true,
				displayMsg : "Displaying items {0} - {1} of {2}",
				emptyMsg : "There are no recipes in the system"
			}
		];
    },

    /**
     * buildUI
     */
    buildUI : function() {
    },

    /**
     * onApplyFilter
     */
    onApplyFilter : function(btn, ev) {
		var filters = {};

		// Reset paging parameters to first page
		filters["start"] = 0;
		filters["limit"] = 10;
		filters["query"] = this.filterField.getValue();

		this.store.load({
			params: filters
		});
    },

    /**
     * onResetFilter
     */
    onResetFilter : function(btn, ev) {
		this.filterField.reset();
    },


    /**
     * onSave
     */
    onSave : function(btn, ev) {
        this.store.save();
    },

    /**
     * onAdd
     */
    onAdd : function(btn, ev) {
        var r = new this.store.recordType({
            name : '',
            brewNotes: '',
            tasteNotes : '',
			yeast : ''
        });
        this.stopEditing();
        this.store.insert(0, r);
        this.startEditing(0, 1);
    },

    /**
     * onDelete
     */
    onDelete : function(btn, ev) {
        var index = this.getSelectionModel().getSelectedCell();
        if (!index) {
            return false;
        }
        var rec = this.store.getAt(index[0]);

		if ( rec.get('name') ) {
			Ext.MessageBox.confirm('Delete' , 'Are you sure you want to delete recipe: ' + rec.get('name') +'?', function(btn){
				if(btn === 'yes'){
				   this.store.remove(rec);
				   this.store.save();
				}

			});
		}
    }
});