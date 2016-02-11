'use strict';

var SprintsSelectComponent = React.createClass({
    handleChange : function (event) {
        if (event.target.value) {
            this.props.onSprintChange(event.target.value);
        }
    },
    render: function () {
        var sprints = this.props.sprints;
        return (
            <div className="form-group">
                <label>Select</label>
                <select className="form-control" onChange={this.handleChange}>
                    <option value="" key="unknown">Choose sprint</option>
                    {sprints.map(sprint =>
                        <option value={sprint.id} key={sprint.id}>
                            {sprint.sprintName}
                        </option>
                    )}
                </select>
            </div>
        )
    }
});

var SprintAddComponent = React.createClass({
    getInitialState: function() {
        return {
            sprintName: '',
            start: '',
            end: ''
        };
    },
    handleChange: function(event) {
        var state = {};
        state[event.target.name] = event.target.value;
        this.setState(state);
    },
    handleAddSprint: function() {
        //TODO: add normal check;
        var sprintInfo = this.state;
        if (true) {
            this.props.onSprintAdd(sprintInfo);
            this.setState(this.getInitialState());
        }
    },
    render: function () {
        return (
            <div className="form-group">
                <button type="button" className="btn btn-primary" data-toggle="modal" data-target="#myModal">Create new Sprint</button>

                <div className="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 className="modal-title" id="myModalLabel">Create new sprint</h4>
                            </div>
                            <div className="modal-body">
                                <form>
                                    <div className="form-group">
                                        <label htmlFor="sprintName">Name</label>
                                        <input className="form-control" id="sprintName" name="sprintName" placeholder="Sprint Name" onChange={this.handleChange}/>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="start">Start date</label>
                                        <input type="date" className="form-control" id="start" name="start" placeholder="Start date" onChange={this.handleChange}/>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="end">End date</label>
                                        <input type="date" className="form-control" id="end" name="end" placeholder="End date" onChange={this.handleChange}/>
                                    </div>
                                </form>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" className="btn btn-primary" data-dismiss="modal" onClick={this.handleAddSprint}>Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        )
    }
});

var StoryAddComponent = React.createClass({
    getInitialState: function() {
        return {
            storyName: '',
            storyDescription: ''
        };
    },
    handleChange: function(event) {
        var state = {};
        state[event.target.name] = event.target.value;
        this.setState(state);
    },
    handleAddStory: function() {
        this.props.onStoryAdd(this.state);
        this.setState(this.getInitialState());
    },
    render: function () {
        return (
            <div className="col-md-6">
                <div className="panel panel-success" >
                    <div className="panel-heading">
                        <h3 className="panel-title">Add story to sprint</h3>
                    </div>
                    <div className="panel-body">
                        <div className="form-group">
                            <div>
                                <label>Story id</label>
                                <input type="text" className="form-control" placeholder="Story id" name="storyName" onChange={this.handleChange} />
                            </div>
                            <div>
                                <label>Story name</label>
                                <input type="text" className="form-control" placeholder="Story name" name="storyDescription" onChange={this.handleChange} />
                            </div>
                            <div>
                                <button type="button" className="form-control btn btn-success" onClick={this.handleAddStory}>Add story</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

var TeamMembers = React.createClass({
    dragStart : function(e) {
        console.log("start drag of Team member: ", e);
        e.dataTransfer.setData("text", "asdsd");
        e.dataTransfer.setData("json", {
            link : e.target.getAttribute('data-member-id'),
            name : e.target.getAttribute('data-member-name')
        });
    },
    render: function () {
        return (
            <div className="clearfix TeamMember">
                {this.props.members.map(member =>
                    <div draggable="true" onDragStart={this.dragStart} data-member-name={member.name} data-member-id={member._links.self.href}
                         key={member._links.self.href} className="pull-left">
                        <img src={member.icon} alt={member.name} className="img-circle" />
                    </div>
                )}
            </div>
        )
    }
});

var SprintPlanning = React.createClass({
    render: function () {
        return (
            <div className="clearfix">
                <h2>Sprint plan</h2>
            </div>
        )
    }
});

var App = React.createClass({
    getInitialState: function () {
        var template = Handlebars.compile(document.getElementById('storyPlanTemplate').innerHTML);
        var items = new vis.DataSet();
        items.on('remove', this.handleRemoveStoryPlan);
        items.on('update', this.handleUpdateStoryPlan);
        var timeline = new vis.Timeline(document.getElementById('sprintplan'), items, {
            editable: true,
            template : template,
            zoomMin: 1000 * 60 * 60 * 24 * 5,
            zoomMax: 1000 * 60 * 60 * 24 * 20
        });
        return {
            members: [],
            sprints: [],
            selectedSprint : {},
            items : items,
            timeline : timeline
        };
    },
    dropTeamMember : function(ev) {
        ev.preventDefault();
        console.log("Drag :", ev);
        var data = ev.dataTransfer.getData("json");
    },
    componentDidMount: function () {
        var that = this;
        $.ajax({url: "/api/teamMembers"}).then(function(data) {
            that.setState({members: data._embedded.teamMembers});
        });

        $.ajax({ url: "/api/sprints"}).then(function(data) {
            that.setState({sprints : data._embedded.sprints});
        });

        $("body").on("drop", ".StoryPlan", this.dropTeamMember);
        $("body").on("dragover", ".StoryPlan", function(ev) {ev.preventDefault();});
    },
    handleSprintChange: function(sprintKey) {
        var selectedSprint = this.state.sprints.find(function(element, index, array) {
            return element.id == sprintKey;
        });
        var items = this.state.items;
        var timeline = this.state.timeline;
        var that = this;
        $.ajax({url: selectedSprint._links.plans.href}).then(function(data) {
            items.clear("refresh");
            timeline.setOptions({
                min: selectedSprint.start,
                max: selectedSprint.end
            });
            var plans = data._embedded.storyPlans;
            plans.forEach(function(item) {
                var currentItem = item;
                if (!currentItem.story) {
                    $.ajax({url: currentItem._links.story.href}).then(function (data) {
                        currentItem.story = data;
                        items.add(currentItem);
                        timeline.fit();
                    });
                }
            });
            that.setState({selectedSprint : selectedSprint});
        });
    },
    handleAddSprint: function(sprint) {
        var that = this;
        sprint.start = new Date(Date.parse(sprint.start));
        sprint.start.setHours(0, 0);
        sprint.end = new Date(Date.parse(sprint.end));
        sprint.end.setHours(23, 59);

        $.ajax({
            url: "/api/sprints",
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sprint)
        }).then(function(data) {
            var newState = that.state;
            newState.sprints.push(data);
            that.setState(newState);
        });
    },
    handleStoryAdding : function(story) {
        var that = this;
        var sprint = this.state.selectedSprint;
        $.ajax({
            url: '/api/stories',
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(story)
        }).then(function(data) {
            var sprintPlan = {
                start : sprint.start,
                end : new Date(Date.parse(sprint.start) + (24 * 60 * 60 * 100)),
                story : data._links.self.href,
                sprint : sprint._links.self.href
            };

            $.ajax({
                url: "/api/storyPlans",
                type: "POST",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sprintPlan)
            }).then(function(data) {
                data.story = story;
                that.state.items.add(data);
                that.state.timeline.fit();
            });
        });
    },
    handleUpdateStoryPlan : function (event, properties, senderId) {
        console.log('event:', event, 'properties:', properties, 'senderId:', senderId);
        var modifiedPlan = properties.data[0];
        $.ajax({
            url: modifiedPlan._links.self.href,
            type: "PUT",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                start : modifiedPlan.start,
                end : modifiedPlan.end
            })
        });
    },
    handleRemoveStoryPlan : function (event, properties, senderId) {
        console.log('event:', event, 'properties:', properties, 'senderId:', senderId);
        if (senderId === "refresh") return;

        $.ajax({
            url: '/api/storyPlans/' + properties.items[0],
            type: "DELETE",
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        });
    },
    render: function () {
        return (
            <div>
                <div className="col-md-6">
                    <div className="panel panel-primary">
                        <div className="panel-heading">
                            <h3 className="panel-title">Choose active Sprint</h3>
                        </div>
                        <div className="panel-body">
                            <SprintsSelectComponent sprints={this.state.sprints} onSprintChange={this.handleSprintChange}/>
                            <SprintAddComponent onSprintAdd={this.handleAddSprint}/>
                        </div>
                    </div>
                </div>
                <StoryAddComponent onStoryAdd={this.handleStoryAdding} />
                <hr />
                <TeamMembers members={this.state.members}/>
                <SprintPlanning sprint={this.state.selectedSprint} plans={this.state.currentSprintPlan}/>
            </div>
        )
    }
});

ReactDOM.render(
    <App />,
    document.getElementById('appContainer')
);