import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import authHeader from "../services/auth-header";

export default function UserHome() {
  
  const [campaigns, setCampaigns] = useState([]);
  
  const { id } = useParams();

  const API_URL = 'https://crowdfunding-back.onrender.com/api/campaigns';

  useEffect(() => {
    loadCampaign();
  }, []);

  const loadCampaign = async () => {
    
        const result = await axios.get(API_URL, { headers: authHeader() });
        setCampaigns(result.data);
    
  };


  return (
    <div className="container">
      <div className="py-4">
    
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">Название</th>
              <th scope="col">Описание</th>
              <th scope="col">Автор проекта</th>
              <th scope="col">Текущая сумма</th>
              <th scope="col">Итоговая сумма</th>
              <th scope="col">Дата окончания сбора</th>
            </tr>
          </thead>
          <tbody>
            {campaigns.map((campaign) => (
              <tr>
                <td>{campaign.title}</td>
                <td>{campaign.description}</td>
                <td>{campaign.author}</td>
                <td>{campaign.currentPrice}</td>
                <td>{campaign.totalPrice}</td>
                <td>{campaign.endDate}</td>
                <td>
                  <Link
                    style={{color: "#FFFFFF", backgroundColor: "#c75ce7d5"}}
                    className="btn btn-outline-primary mx-2"
                    to={`/paid`}
                  >
                    Пожертвовать
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
