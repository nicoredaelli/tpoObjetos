import React from 'react'
import { Routes, Route } from 'react-router-dom'
import { AuthProvider } from './contexts/AuthContext'
import { PartidoProvider } from './contexts/PartidoContext'
import Layout from './components/Layout'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Dashboard from './pages/Dashboard'
import CrearPartido from './pages/CrearPartido'
import MisPartidos from './pages/MisPartidos.jsx'
import PartidoDetalle from './pages/PartidoDetalle'
import Perfil from './pages/Perfil'
import ProtectedRoute from './components/ProtectedRoute'

function App() {
  return (
    <AuthProvider>
      <PartidoProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route path="dashboard" element={
              <ProtectedRoute>
                <Dashboard />
              </ProtectedRoute>
            } />
            <Route path="crear-partido" element={
              <ProtectedRoute>
                <CrearPartido />
              </ProtectedRoute>
            } />
            <Route path="mis-partidos" element={
              <ProtectedRoute>
                <MisPartidos />
              </ProtectedRoute>
            } />
            <Route path="partido/:id" element={
              <ProtectedRoute>
                <PartidoDetalle />
              </ProtectedRoute>
            } />
            <Route path="perfil" element={
              <ProtectedRoute>
                <Perfil />
              </ProtectedRoute>
            } />
          </Route>
        </Routes>
      </PartidoProvider>
    </AuthProvider>
  )
}

export default App