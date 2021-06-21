import React from 'react';
import ReactDOM from 'react-dom';
import H2Service from '../services/H2Service';
import { connect } from 'react-redux'
import { Form, Button } from 'react-bootstrap';
import { Formik } from 'formik';
import * as yup from 'yup';

class CreateEmployee extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			departmentIDsAndNames: []
		};
		this.dbName='employees'
		this.attributes = {
			'fullName': 'Full Name',
			'dep': 'Department ID',
			'jobTitle': 'Job Title',
			'yearlySalary': 'Salary'
		};
		this.initValues = {
			'fullName': '',
			'dep': '',
			'jobTitle': '',
			'yearlySalary': ''
		}
		this.schema = yup.object({
			fullName: yup.string()
				.max(45,'Your entry is too long')
				.required('Full Name is required'),
			dep: yup.string()
				.required('Please choose one'),
			jobTitle: yup.string()
				.max(45,'Title too long')
				.required('Job Title is Required'),
			yearlySalary: yup.number()
				.typeError('Yearly Salary must be a number')
				.positive('Yearly Salary must be positive')
				.required('Yearly Salary is Required')
		})
		this.onCreate = H2Service.onCreate.bind(this);
	}
	
	componentDidMount() {
		H2Service.getDepartments().then((response) => {
			const data = response.data.content;
			var depIDsAndNames = [];
			for (const d in data) {
				depIDsAndNames.push([data[d].depName,data[d].dep_ID]);
			}
			this.setState({
				departmentIDsAndNames: depIDsAndNames
			})
		})
	}

	checkDepType(attribute) {
		if ('dep' === attribute.toString()) {
			return { as: "select" };
		} else {
			return { as: "input" };
		}
	}

	depOptions(attribute) {
		if ('dep' === attribute.toString()) {
			return (<>
				<option key='default' value=''>Choose...</option>
				{this.state.departmentIDsAndNames.map(depID =>
					<option key={`${depID[1]}`} value={`${depID[1]}`}>
						{depID[0]}
					</option>)}
				</>);
		} else {
			return null;
		}
	}

	inputs(handleChange, handleBlur, values, touched, errors) {
		return ( 
			Object.keys(this.attributes).map(attribute =>	
				<div key={attribute} className="mb-3">
					<Form.Group controlId={"formCreateEmployee.".concat(
						attribute)} >
						<Form.Label>{this.attributes[attribute]}</Form.Label>
						<Form.Control
							name={attribute}
							as={this.checkDepType(attribute).as}
							type="text"
							value={values[attribute]}
							onChange={handleChange}
							onBlur={handleBlur}
							isInvalid={touched[attribute] && errors[attribute] ? true : false}
						>
							{this.depOptions(attribute)}
						</Form.Control>
						<Form.Control.Feedback type="invalid">
							{touched[attribute] && errors[attribute] ? 
								errors[attribute] : null }
						</Form.Control.Feedback>
					</Form.Group>
				</div>
			)
		)
	}

	render() {
		return (
			<div id="CreateEmployee" className="modalDialog">
				<a href="/" title="Close" className="close">[X]</a>

				<h2>Create New Employee</h2>

				<Formik
					validationSchema={this.schema}
					onSubmit= {(values,{setSubmitting, resetForm}) => {
						setSubmitting(true);
						this.onCreate(this.dbName, values)
						resetForm();
						setSubmitting(false)
					}}
					initialValues={this.initValues}
				>
					{({
						handleSubmit,
						handleChange,
						handleBlur,
						values,
						touched,
						errors,
					}) => (
						<Form noValidate onSubmit={handleSubmit} className='mx-auto'>
							{this.inputs(handleChange, handleBlur, values, touched, errors)}
							<Button type='submit' disabled={Form.isSubmitting}>Create</Button>
						</Form>
					)}
				</Formik>
			</div>
		)
	}
}

const mapStateToProps = (state) => ({
	errors: state.errors
});

export default connect(mapStateToProps)(CreateEmployee);
