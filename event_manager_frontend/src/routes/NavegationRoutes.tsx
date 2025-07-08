import { Navigate, Route, Routes } from "react-router-dom";
import Login from "../components/session/Login";
import Register from "../components/session/Register";
import Logout from "../components/session/Logout";
import RecitalPage from "../pages/RecitalPage";
import PlayPage from "../pages/PlayPage";
import ConferencePage from "../pages/Conference";
import Home from "../pages/Home";
import ReservationPage from "../pages/ReservationPage";
import type { JSX } from "react";

const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
  const isAdmin = localStorage.getItem("token") === "ADMIN";
  return !isAdmin ? children : <Navigate to="/home" />;
};

const NavegationRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/logout" element={<Logout />} />
      <Route path="/home" element={<Home />} />

      <Route path="/recitals" element={<RecitalPage />} />
      <Route path="/plays" element={<PlayPage />} />
      <Route path="/conferences" element={<ConferencePage />} />
      <Route
        path="/reservations"
        element={
          <ProtectedRoute>
            <ReservationPage />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};

export default NavegationRoutes;
