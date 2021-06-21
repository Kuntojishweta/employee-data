import React from 'react';
import ReactDOM from 'react-dom';
import H2Service from '../services/H2Service';
import { connect } from 'react-redux'

class CreateDepartment extends React.Component {

	constructor(props) {
		super(props);
		this.attributes = {
			'depName': 'Department Name',
			'depHead': 'Department Head'
		};
		this.handleSubmit = this.handleSubmit.bind(this);
		this.onCreate = H2Service.onCreate.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault(); //stops the event from bubbling further up the hierarchy
		const newEntry = {};
		const DbName = 'departments';
		Object.keys(this.attributes).forEach(attribute => {
			newEntry[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.onCreate(DbName,newEntry);

		// clear out the dialog inputs
		Object.keys(this.attributes).forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
		});

		// Navigate away from the dialog to hide it.
		window.location = "#";
	}

	render() {

		const inputs = Object.keys(this.attributes).map(attribute=>
			<p key={attribute}>
				<input type="text" placeholder={this.attributes[attribute]} ref={attribute} className="field"/>
			</p>
		);

		return (
			<div>
				<a href="#CreateDepartment">Create Department</a>

				<div id="CreateDepartment" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">[X]</a>

						<h2>Create new employee</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Create</button>
						</form>
					</div>
				</div>
			</div>
		)
	}
}

////// Page Connection

const mapStateToProps = (state) => ({
	errors: state.errors
});

export default connect(mapStateToProps)(CreateDepartment);
