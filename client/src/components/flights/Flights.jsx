import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, Col,Row } from "react-bootstrap";

const Flights = () => {
  const [flights, setFlights] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/flights")
      .then((response) => {
        setFlights(response.data);
      })
      .catch((error) => {
        console.error("Error fetching companies:", error);
      });
  }, []);
  return (
    <div className="container my-5">
      <h3 className="text-center"> All Flights </h3>

      <Row xs={1} md={2} lg={3} className="g-4 justify-content-center">
        {flights.map((flight, index) => (
          <Col key={index}>
          <Card
            key={index}
            className="text-center m-3 p-1 mx-auto"
            style={{ width: "17rem" }}
          >
            <Card.Title>
              {flight.origin} / {flight.destiny}
            </Card.Title>
            <h5 className="mb-2 text-muted">
              {" "}
              Price: ${flight.convertedPrice}
            </h5>

            <img
              src={flight.company.banner}
              alt=""
              style={{ width: "2rem" }}
              className="rounded-circle mx-auto"
            />
                                                
            <h5>{flight.company.name}</h5>
            <p>
              Departure: {flight.departureTime} Arriving:{flight.arrivingTime}{" "}
            </p>
            <p>Frequency: {flight.frequency}</p>
          </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default Flights;
