import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card } from "react-bootstrap";

const Flights = () => {
  const [flights, setFlights] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/flights/dolar-flights")
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

      <div className="w-70 d-flex justify-content-evenly flex-wrap m-3">
        {flights.map((flight, index) => (
          <Card
            key={index}
            className="text-center m-3 p-1"
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
        ))}
      </div>
    </div>
  );
};

export default Flights;
