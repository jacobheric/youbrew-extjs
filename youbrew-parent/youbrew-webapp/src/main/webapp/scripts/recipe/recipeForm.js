/**
 *
 * 	 Youbrew sample starter app recipe form.
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
Ext.ns('youbrew', 'youbrew.recipe');
/**
 * @class youbrew.recipe.form
 * An recipe FormPanel, clearly ripped right from the extjs examples
 */
youbrew.recipe.Form = Ext.extend(Ext.form.FormPanel, {
    renderTo: 'youbrew-form',
    iconCls: 'silk-user',
    frame: true,
    labelAlign: 'right',
    title: 'YouBrew Recipe Form ',
    frame: true,
    width: 900,
    defaultType: 'textfield',
    defaults: {
        anchor: '100%'
    },

    // private A pointer to the currently loaded record
    record : null,

	yeastStore : null,

	yeastCombo : null,

    /**
     * initComponent
     * @protected
     */
    initComponent : function() {
        // build the form-fields.  Always a good idea to defer form-building to a method so that this class can
        // be over-ridden to provide different form-fields
        this.items = this.buildForm();

        // build form-buttons
        this.buttons = this.buildUI();

        // add a create event for convenience in our application-code.
        this.addEvents({
            /**
             * @event create
             * Fires when user clicks [create] button
             * @param {FormPanel} this
             * @param {Object} values, the Form's values object
             */
            create : true
        });

        // super
        youbrew.recipe.Form.superclass.initComponent.call(this);
    },

    /**
     * buildform
     * @private
     */
    buildForm : function() {
        return [
            {fieldLabel: 'Name', name: 'name', allowBlank: false},
			{fieldLabel: 'Brew Notes', name: 'brewNotes', allowBlank: false},
			{fieldLabel: 'Taste Notes', name: 'tasteNotes', allowBlank: false},
			new Ext.form.ComboBox({
						name: 'yeast',
						fieldLabel: 'Yeast', 
						editable: false,
						store: this.yeastStore,
						mode: 'remote',
						ref: 'yeastCombo',
						triggerAction: 'all',
						lazyRender: true,
						listClass: 'x-combo-list-small',
						displayField: 'name',
						valueField: 'name',
						hiddenValue: 'name',
						hiddenName: 'yeastName',
						tpl:'<tpl for="."><div class="x-combo-list-item">{name} - {description}</div></tpl>'
					}),

			//No date time picker, so leave for now 
			/*{fieldLabel: 'Start', name: 'start', allowBlank: true},
			{fieldLabel: 'End', name: 'end', allowBlank: true}*/
        ];
    },

    /**
     * buildUI
     * @private
     */
    buildUI: function(){
        return [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: this.onUpdate,
            scope: this
        }, {
            text: 'Create',
            iconCls: 'silk-user-add',
            handler: this.onCreate,
            scope: this
        }, {
            text: 'Reset',
            handler: function(btn, ev){
                this.getForm().reset();
            },
            scope: this
        }];
    },

    /**
     * loadRecord
     * @param {Record} rec
     */
    loadRecord : function(rec) {
        this.record = rec;
        this.getForm().loadRecord(rec);
    },

    /**
     * onUpdate
     */
    onUpdate : function(btn, ev) {
        if (this.record == null) {
            return;
        }
        if (!this.getForm().isValid()) {
            App.setAlert(false, "Form is invalid.");
            return false;
        }
        this.getForm().updateRecord(this.record);
    },

    /**
     * onCreate
     */
    onCreate : function(btn, ev) {
        if (!this.getForm().isValid()) {
            App.setAlert(false, "Form is invalid");
            return false;
        }
        this.fireEvent('create', this, this.getForm().getValues());
        this.getForm().reset();
    },

    /**
     * onReset
     */
    onReset : function(btn, ev) {
        this.fireEvent('update', this, this.getForm().getValues());
        this.getForm().reset();
    }
});