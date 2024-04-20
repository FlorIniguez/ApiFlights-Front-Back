import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";

const NavbarApi = () => {
  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Link className="nav-link" to="/">
            <Navbar.Brand>ApiFlights</Navbar.Brand>
          </Link>

          <Nav className="me-auto">
            <Link className="nav-link" to="/">
              Home
            </Link>
            <Link className="nav-link" to="/companies">
              Companies
            </Link>{" "}
            <Link className="nav-link" to="/flights">
              Flights
            </Link>{" "}
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default NavbarApi;
