/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    /*var models = app.module("models");
    var views = app.module("views");*/
    
    var AppRouter = Backbone.Router.extend({
        routes: {
            '': 'list',
            'new': 'create'
            ,
            ':id': 'details'
        },
        initialize: function() {
            var self = this;
            $('#create').html(new IDEA.views.CreateView({
                // tpl-create is template identifier for 'create' block
                templateName: '#tpl-create',
                navigate: function() {
                    self.navigate('new', true);
                }
            }).render().el);
        },
        list: function() {
            this.collection = new IDEA.collections.IdeaCollection();
            var self = this;
            this.collection.fetch({
                success: function() {
                    self.listView = new IDEA.views.ListView({
                        model: self.collection,
                        // tpl-idea-list-itemis template identifier for item
                        templateName: '#tpl-idea-list-item'
                    });
                    $('#datatable').html(self.listView.render().el).append(_.template($('#thead').html())());
                    if (self.requestedId) {
                        self.details(self.requestedId);
                    }
                    var pagerOptions = {
                        // target the pager markup 
                        container: $('.pager'),
                        // output string - default is '{page}/{totalPages}'; possiblevariables: {page}, {totalPages},{startRow}, {endRow} and {totalRows}
                        output: '{startRow} to {endRow} ({totalRows})',
                        // starting page of the pager (zero based index)
                        page: 0,
                        // Number of visible rows - default is 10
                        size: 10
                    };
                    $('#datatable').tablesorter({widthFixed: true,
                        widgets: ['zebra']}).
                            tablesorterPager(pagerOptions);
                }
            });
        },
        details: function(id) {
            if (this.collection) {
                this.idea = this.collection.get(id);
                if (this.view) {
                    this.view.close();
                }
                var self = this;
                this.view = new IDEA.views.ModelView({
                    model: this.idea,
                    // tpl-idea-details is template identifier for chosen model element
                    templateName: '#tpl-idea-details',
                    getHashObject: function() {
                        return self.getData();
                    }
                });
                $('#details').html(this.view.render().el);
            } else {
                this.requestedId = id;
                this.list();
            }
        },
        create: function() {
            if (this.view) {
                this.view.close();
            }
            var self = this;
            var dataModel = new IDEA.models.Idea();
            // see isNew() method implementation in the model
            dataModel.notSynced = true;
            this.view = new IDEA.views.ModelView({
                model: dataModel,
                collection: this.collection,
                // tpl-idea-details is a template identifier for chosen model element
                templateName: '#tpl-idea-details',
                navigate: function(id) {
                    self.navigate(id, false);
                },
                getHashObject: function() {
                    return self.getData();
                }
            });
            $('#details').html(this.view.render().el);
        },
        getData: function() {
            return {
                id: $('#id').val(),
                title: $('#title').val(),
                description: $('#description').val()
            };
        }
    });
    new AppRouter();
    
    
    Backbone.history.start();
});
