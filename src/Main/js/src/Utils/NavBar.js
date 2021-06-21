import React from 'react';
import { Navbar, NavDropdown, Nav, Form, FormControl, Button } from 'react-bootstrap';

export default class NavBar extends React.Component {
    render() {
        return (
            <Navbar bg="primary" variant="dark" expand="lg">
                <Navbar.Brand href="#home">Washington Red Tails</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                    <NavDropdown title="Personnel" id="basic-nav-dropdown">
                            <NavDropdown.Item href="/EmployeeTable">See all Personnel</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/CreateEmployee">Add Employee</NavDropdown.Item>
                        </NavDropdown>
                        <NavDropdown title="Organization" id="basic-nav-dropdown">
                            <NavDropdown.Item href="/DepartmentTable">Department Overview</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/CreateDepartment">Add a Department</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    <Form inline>
                        <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                        <Button variant="outline-secondary">Search</Button>
                    </Form>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}
