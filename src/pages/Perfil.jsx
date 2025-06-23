import React, { useState } from 'react'
import { useAuth } from '../contexts/AuthContext'
import { 
  User, 
  Mail, 
  Bell, 
  MapPin,
  Edit,
  Save,
  X,
  CheckCircle,
  AlertCircle
} from 'lucide-react'
import DeporteBadge from '../components/DeporteBadge'
import NivelBadge from '../components/NivelBadge'

const Perfil = () => {
  const { user, updateUser } = useAuth()
  const [isEditing, setIsEditing] = useState(false)
  const [formData, setFormData] = useState({
    nombre: user?.nombre || '',
    correo: user?.correo || '',
    deportesFavoritos: user?.deportesFavoritos || [],
    nivelesDeportes: user?.nivelesDeportes || {},
    medioNotificacion: user?.medioNotificacion || 'EMAIL'
  })
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  const deportes = [
    { value: 'FUTBOL', label: 'Fútbol', emoji: '⚽' },
    { value: 'BASQUET', label: 'Básquet', emoji: '🏀' },
    { value: 'VOLEY', label: 'Vóley', emoji: '🏐' }
  ]

  const niveles = [
    { value: 'PRINCIPIANTE', label: 'Principiante' },
    { value: 'INTERMEDIO', label: 'Intermedio' },
    { value: 'AVANZADO', label: 'Avanzado' }
  ]

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target
    
    if (name === 'deportesFavoritos') {
      if (checked) {
        setFormData(prev => ({
          ...prev,
          deportesFavoritos: [...prev.deportesFavoritos, value],
          nivelesDeportes: {
            ...prev.nivelesDeportes,
            [value]: 'PRINCIPIANTE'
          }
        }))
      } else {
        setFormData(prev => ({
          ...prev,
          deportesFavoritos: prev.deportesFavoritos.filter(d => d !== value),
          nivelesDeportes: Object.fromEntries(
            Object.entries(prev.nivelesDeportes).filter(([key]) => key !== value)
          )
        }))
      }
    } else if (name.startsWith('nivel_')) {
      const deporte = name.replace('nivel_', '')
      setFormData(prev => ({
        ...prev,
        nivelesDeportes: {
          ...prev.nivelesDeportes,
          [deporte]: value
        }
      }))
    } else {
      setFormData(prev => ({
        ...prev,
        [name]: value
      }))
    }
    
    if (error) setError('')
    if (success) setSuccess('')
  }

  const handleSave = () => {
    if (!formData.nombre.trim()) {
      setError('El nombre es requerido')
      return
    }
    if (!formData.correo.trim()) {
      setError('El correo es requerido')
      return
    }
    if (formData.deportesFavoritos.length === 0) {
      setError('Selecciona al menos un deporte')
      return
    }

    updateUser(formData)
    setIsEditing(false)
    setSuccess('Perfil actualizado correctamente')
    setError('')
  }

  const handleCancel = () => {
    setFormData({
      nombre: user?.nombre || '',
      correo: user?.correo || '',
      deportesFavoritos: user?.deportesFavoritos || [],
      nivelesDeportes: user?.nivelesDeportes || {},
      medioNotificacion: user?.medioNotificacion || 'EMAIL'
    })
    setIsEditing(false)
    setError('')
    setSuccess('')
  }

  if (!user) {
    return (
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="card">
          <div className="card-body text-center py-12">
            <AlertCircle className="mx-auto h-12 w-12 text-gray-400 mb-4" />
            <h3 className="text-lg font-medium text-gray-900 mb-2">
              Error al cargar perfil
            </h3>
            <p className="text-gray-500">
              No se pudo cargar la información del usuario.
            </p>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold text-gray-900">Mi Perfil</h1>
            <p className="text-gray-600 mt-2">
              Gestiona tu información personal y preferencias deportivas
            </p>
          </div>
          {!isEditing ? (
            <button
              onClick={() => setIsEditing(true)}
              className="btn-primary"
            >
              <Edit size={16} className="mr-2" />
              Editar Perfil
            </button>
          ) : (
            <div className="flex space-x-2">
              <button
                onClick={handleSave}
                className="btn-success"
              >
                <Save size={16} className="mr-2" />
                Guardar
              </button>
              <button
                onClick={handleCancel}
                className="btn-secondary"
              >
                <X size={16} className="mr-2" />
                Cancelar
              </button>
            </div>
          )}
        </div>
      </div>

      {/* Mensajes */}
      {error && (
        <div className="mb-6 p-4 bg-error-50 border border-error-200 rounded-lg flex items-center space-x-2 text-error-700">
          <AlertCircle size={20} />
          <span>{error}</span>
        </div>
      )}

      {success && (
        <div className="mb-6 p-4 bg-success-50 border border-success-200 rounded-lg flex items-center space-x-2 text-success-700">
          <CheckCircle size={20} />
          <span>{success}</span>
        </div>
      )}

      <div className="grid lg:grid-cols-3 gap-8">
        {/* Información Personal */}
        <div className="lg:col-span-2 space-y-6">
          <div className="card">
            <div className="card-header">
              <h2 className="text-xl font-semibold text-gray-900">Información Personal</h2>
            </div>
            <div className="card-body space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Nombre Completo
                </label>
                {isEditing ? (
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <User className="h-5 w-5 text-gray-400" />
                    </div>
                    <input
                      type="text"
                      name="nombre"
                      value={formData.nombre}
                      onChange={handleChange}
                      className="input pl-10"
                      placeholder="Tu nombre completo"
                    />
                  </div>
                ) : (
                  <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
                    <User className="h-5 w-5 text-gray-400" />
                    <span className="text-gray-900">{user.nombre}</span>
                  </div>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Correo Electrónico
                </label>
                {isEditing ? (
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <Mail className="h-5 w-5 text-gray-400" />
                    </div>
                    <input
                      type="email"
                      name="correo"
                      value={formData.correo}
                      onChange={handleChange}
                      className="input pl-10"
                      placeholder="tu@email.com"
                    />
                  </div>
                ) : (
                  <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
                    <Mail className="h-5 w-5 text-gray-400" />
                    <span className="text-gray-900">{user.correo}</span>
                  </div>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Medio de Notificación
                </label>
                {isEditing ? (
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <Bell className="h-5 w-5 text-gray-400" />
                    </div>
                    <select
                      name="medioNotificacion"
                      value={formData.medioNotificacion}
                      onChange={handleChange}
                      className="input pl-10"
                    >
                      <option value="EMAIL">Email</option>
                      <option value="SMS">SMS</option>
                    </select>
                  </div>
                ) : (
                  <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
                    <Bell className="h-5 w-5 text-gray-400" />
                    <span className="text-gray-900">
                      {user.medioNotificacion === 'EMAIL' ? 'Email' : 'SMS'}
                    </span>
                  </div>
                )}
              </div>
            </div>
          </div>

          {/* Preferencias Deportivas */}
          <div className="card">
            <div className="card-header">
              <h2 className="text-xl font-semibold text-gray-900">Preferencias Deportivas</h2>
            </div>
            <div className="card-body space-y-6">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-3">
                  Deportes Favoritos
                </label>
                {isEditing ? (
                  <div className="space-y-2">
                    {deportes.map(deporte => (
                      <label key={deporte.value} className="flex items-center space-x-3 p-3 border border-gray-200 rounded-lg hover:bg-gray-50 cursor-pointer">
                        <input
                          type="checkbox"
                          name="deportesFavoritos"
                          value={deporte.value}
                          checked={formData.deportesFavoritos.includes(deporte.value)}
                          onChange={handleChange}
                          className="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300 rounded"
                        />
                        <span className="text-lg">{deporte.emoji}</span>
                        <span className="text-sm font-medium text-gray-900">{deporte.label}</span>
                      </label>
                    ))}
                  </div>
                ) : (
                  <div className="flex flex-wrap gap-2">
                    {user.deportesFavoritos.map(deporte => (
                      <DeporteBadge key={deporte} deporte={deporte} size="lg" />
                    ))}
                  </div>
                )}
              </div>

              {/* Niveles por Deporte */}
              {(isEditing ? formData.deportesFavoritos : user.deportesFavoritos).length > 0 && (
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-3">
                    Nivel por Deporte
                  </label>
                  <div className="space-y-3">
                    {(isEditing ? formData.deportesFavoritos : user.deportesFavoritos).map(deporte => {
                      const deporteInfo = deportes.find(d => d.value === deporte)
                      const nivel = isEditing 
                        ? formData.nivelesDeportes[deporte] 
                        : user.nivelesDeportes[deporte]
                      
                      return (
                        <div key={deporte} className="flex items-center space-x-4 p-3 bg-gray-50 rounded-lg">
                          <span className="text-lg">{deporteInfo.emoji}</span>
                          <span className="text-sm font-medium text-gray-900 min-w-0 flex-1">
                            {deporteInfo.label}
                          </span>
                          {isEditing ? (
                            <select
                              name={`nivel_${deporte}`}
                              value={formData.nivelesDeportes[deporte] || 'PRINCIPIANTE'}
                              onChange={handleChange}
                              className="input w-auto min-w-0"
                            >
                              {niveles.map(nivel => (
                                <option key={nivel.value} value={nivel.value}>
                                  {nivel.label}
                                </option>
                              ))}
                            </select>
                          ) : (
                            <NivelBadge nivel={nivel} />
                          )}
                        </div>
                      )
                    })}
                  </div>
                </div>
              )}
            </div>
          </div>
        </div>

        {/* Panel Lateral */}
        <div className="space-y-6">
          {/* Avatar y Estadísticas */}
          <div className="card">
            <div className="card-body text-center">
              <div className="w-20 h-20 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <User className="h-10 w-10 text-primary-600" />
              </div>
              <h3 className="text-lg font-semibold text-gray-900 mb-1">
                {user.nombre}
              </h3>
              <p className="text-sm text-gray-500 mb-4">
                Miembro desde 2024
              </p>
              
              <div className="grid grid-cols-2 gap-4 text-center">
                <div>
                  <div className="text-2xl font-bold text-primary-600">12</div>
                  <div className="text-xs text-gray-500">Partidos</div>
                </div>
                <div>
                  <div className="text-2xl font-bold text-success-600">8</div>
                  <div className="text-xs text-gray-500">Completados</div>
                </div>
              </div>
            </div>
          </div>

          {/* Ubicación */}
          <div className="card">
            <div className="card-header">
              <h3 className="text-lg font-semibold text-gray-900">Ubicación</h3>
            </div>
            <div className="card-body">
              <div className="flex items-center space-x-3 text-gray-600">
                <MapPin size={20} />
                <div>
                  <p className="font-medium text-gray-900">Buenos Aires</p>
                  <p className="text-sm">Argentina</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Perfil