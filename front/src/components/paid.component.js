import React, { Component } from "react";

import UserService from "../services/user.service";

export default class Paid extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getPublicContent().then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (
      <div sclassName="container">
        <header style={{backgroundColor: "#c75ce7d5"}}  className="jumbotron">
          <h3>Реквизиты для перевода</h3>
          Банковская карта: 5228 6005 6815 6535
          Номер телефона: 89009575615
        </header>
      </div>
    );
  }
}
