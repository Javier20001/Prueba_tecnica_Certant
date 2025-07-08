import Navbar from "./ui/navbar/Navbar";
import NavergationRoutes from "./routes/NavegationRoutes";
import { BrowserRouter } from "react-router-dom";
function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <NavergationRoutes />
    </BrowserRouter>
  );
}

export default App;
