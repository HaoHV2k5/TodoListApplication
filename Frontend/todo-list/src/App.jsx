import { BrowserRouter, Routes, Route} from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import ForgetPass from "./pages/ForgetPass";
import Register from "./pages/Register";
export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/forgetpass" element={<ForgetPass />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </BrowserRouter>
  );
}