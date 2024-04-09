import axios, { AxiosInstance } from "axios";

const API_URL = "http://localhost:8080"

const AxiosInstance: AxiosInstance = axios.create({
    baseURL: API_URL,
    headers: {
        "Content-Type": "application/json",
    },
})

export default AxiosInstance;