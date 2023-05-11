import React, { Component } from "react";

import UserService from "../services/user.service";

export default class Home extends Component {
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
          <h3>Платформа для сбора средств</h3>
          Разработана в рамках курсовой работы.
        </header>
      </div>
    );
  }
}
