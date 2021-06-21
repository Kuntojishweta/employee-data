import React from 'react';
import H2Service from '../services/H2Service';
import ReactPaginate from 'react-paginate';
import { connect } from 'react-redux'

class DepartmentTable extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			departments: [],
			offset: 0,
			perPage: 2,
			currentPage: 0
		};
		this.handlePageClick =
			this.handlePageClick.bind(this);
	}

	componentDidMount() {
		const dbName = 'departments';
		const perPage = this.state.perPage;
		const currentPage = this.state.currentPage;

		H2Service.getRequest(dbName, perPage, currentPage)
			.then((response) => {
				const data = response.data;
				this.setState({
					departments: data.content,
					pageCount: Math.ceil(data.totalElements / this.state.perPage)
				})
			});
	}

	render() {
		return (
			<div>
				<h1 className="text-center">Department List</h1>
				<DepartmentList departments={this.state.departments} />

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

///// DepartmentList //////////////////////////////
class DepartmentList extends React.Component {
	render() {
		const renderDepartments = this.props.departments.map(d =>
			<Department key={d.dep_ID} department={d} />
		);
		return (
			<table className="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Head</th>
					</tr>
				</thead>
				<tbody>
					{renderDepartments}
				</tbody>
			</table>
		)
	}
}

///// Department ////////////////////////////////
class Department extends React.Component {
	render() {
		return (
			<tr>
				<td>{this.props.department.dep_ID}</td>
				<td>{this.props.department.depName}</td>
				<td>{this.props.department.depHead}</td>
			</tr>
		)
	}
}

const mapStateToProps = (state) => ({
	errors: state.errors
});

export default connect(mapStateToProps)(DepartmentTable);
