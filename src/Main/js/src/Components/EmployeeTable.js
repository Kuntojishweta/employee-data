import React from 'react';
import H2Service from '../services/H2Service';
import ReactPaginate from 'react-paginate';
import { connect } from 'react-redux'

class EmployeeTable extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			employees: [],
			offset: 0,
			perPage: 2,
			currentPage: 0
		};
		this.handlePageClick =
			this.handlePageClick.bind(this);
	}

	componentDidMount() {
		const dbName = 'employees';
		const perPage = this.state.perPage;
		const currentPage = this.state.currentPage;

		console.log('Emp Table did Map')

		H2Service.getRequest(dbName, perPage, currentPage)
			.then((response) => {
				const data = response.data;
				this.setState({
					employees: data.content,
					pageCount: Math.ceil(data.totalElements / this.state.perPage)
				})
			});
	}

	render() {
		return (
			<div>
				<h1 className="text-center">Employee List</h1>
				<EmployeeList employees={this.state.employees} />

				{/* <h1 className = "text-center">Department List</h1>
				<DepartmentList departments={this.state.departments}/> */}
				<ReactPaginate
					previousLabel={'previous'}
					nextLabel={'next'}
					breakLabel={'...'}
					pageCount={this.state.pageCount}
					marginPagesDisplayed={2}
					pageRangeDisplayed={this.state.perPage}
					onPageChange={this.handlePageClick}
					subContainerClassName={'pages pagination'}

					breakClassName={'page-item'}
					breakLinkClassName={'page-link'}
					containerClassName={'pagination'}
					pageClassName={'page-item'}
					pageLinkClassName={'page-link'}
					previousClassName={'page-item'}
					previousLinkClassName={'page-link'}
					nextClassName={'page-item'}
					nextLinkClassName={'page-link'}
					activeClassName={'active'}
				/>
			</div>
		)
	}

	///// handlePageClick ///////////////////////////
	handlePageClick = (e) => {
		const selectedPage = e.selected;
		const offset = selectedPage * this.state.perPage;

		this.setState({
			currentPage: selectedPage,
			offset: offset
		}, () => {
			this.componentDidMount()
		});

	};
}

///// EmployeeList //////////////////////////////
class EmployeeList extends React.Component {
	render() {
		const renderEmployees = this.props.employees.map(e =>
			<Employee key={e.empID} employee={e} />
		);
		return (
			<table className="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Full Name</th>
						<th>Department ID</th>
						<th>Salary</th>
						<th>Job Title</th>
					</tr>
				</thead>
				<tbody>
					{renderEmployees}
				</tbody>
			</table>
		)
	}
}

///// Employee //////////////////////////////////
class Employee extends React.Component {
	render() {
		return (
			<tr>
				<td>{this.props.employee.empID}</td>
				<td>{this.props.employee.fullName}</td>
				<td>{this.props.employee.dep}</td>
				<td>{this.props.employee.yearlySalary}</td>
				<td>{this.props.employee.jobTitle}</td>
			</tr>
		)
	}
}

const mapStateToProps = (state) => ({
	errors: state.errors
});

export default connect(mapStateToProps)(EmployeeTable);
