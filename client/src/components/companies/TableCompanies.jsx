import React, { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import axios from "axios";
import "./tableCompanies.css";

const TableCompanies = () => {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/companies")
      .then((response) => {
        setCompanies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching companies:", error);
      });
  }, []);

  return (
    <div className="container mt-5">
      <h3 className="text-center m-3">Companies</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Banner</th>
            <th>Company</th>
            <th>Page</th>
          </tr>
        </thead>

        <tbody>
          {companies.map((company) => (
            <tr key={company.id}>
              <td>
                <img
                  src={company.banner}
                  alt="logos-companies"
                  className="logo"
                />
              </td>
              <td>{company.name}</td>
              <td>
                <a href={company.page}>{company.page}</a>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default TableCompanies;
