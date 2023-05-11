import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import authHeader from "../services/auth-header";

export default function EditCampaign() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [campaign, setCampaign] = useState({
    title: "",
    description: "",
    author: "",
    currentPrice: "",
    totalPrice: "",
    endDate: "",
  });

  const { title, description, author, currentPrice, totalPrice, endDate } = campaign;

  const onInputChange = (e) => {
    setCampaign({ ...campaign, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadCampaign();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/campaigns/${id}`, campaign, { headers: authHeader() });
    navigate("/campaign");
  };

  const loadCampaign = async () => {
    const result = await axios.get(`http://localhost:8080/api/campaigns/${id}`, { headers: authHeader() });
    setCampaign(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Изменение Кампании</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Title" className="form-label">
                Название
              </label>
              <input
                  type={"text"}
                  className="form-control"
                  placeholder="Enter title"
                  name="title"
                  value={title}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Description" className="form-label">
                Описание
              </label>
              <input
                  type={"text"}
                  className="form-control"
                  placeholder="Enter Description"
                  name="description"
                  value={description}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Author" className="form-label">
                Автор проекта
              </label>
              <input
                  type={"text"}
                  className="form-control"
                  placeholder="Enter author"
                  name="author"
                  value={author}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="CurrentPrice" className="form-label">
                Текущая сумма 
              </label>
              <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter current price"
                  name="currentPrice"
                  value={currentPrice}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="TotalPrice" className="form-label">
                Итоговая сумма
              </label>
              <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter total price"
                  name="totalPrice"
                  value={totalPrice}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="EndDate" className="form-label">
                Дата окончания сбора
              </label>
              <input
                  type={"date"}
                  className="form-control"
                  placeholder="Enter end date"
                  name="endDate"
                  value={endDate}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Сохранить
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/campaign">
              Отменить
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
