import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import TableCompanies from "./components/companies/TableCompanies";
import Flights from "./components/flights/Flights";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavbarApi from "./components/navbar/NavbarApi";
import Home from "./components/home/Home";

function App() {
  return (
    <div className="App">
      <Router>
        <NavbarApi />
        <Routes>
          <Route path="/"  Component={Home}/>
          <Route path="/companies" Component={TableCompanies}/>
          <Route path="/flights" Component={Flights}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
