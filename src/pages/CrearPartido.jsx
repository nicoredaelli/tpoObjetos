import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext'
import { usePartidos } from '../contexts/PartidoContext'
import { 
  MapPin, 
  Clock, 
  Users, 
  Calendar,
  AlertCircle,
  CheckCircle
} from 'lucide-react'
import DeporteBadge from '../components/DeporteBadge'
import NivelBadge from '../components/NivelBadge'

const CrearPartido = () => {
  const { user } = useAuth()
  const { crearPartido, loading } = usePartidos()
  const navigate = useNavigate()
  
  const [formData, setFormData] = useState({
    deporte: '',
    cantidadJugadores: '',
    duracion: '',
    ubicacion: {
      latitud: '',
      longitud: '',
      direccion: ''
    },
    horario: '',
    nivel: '',
    emparejamiento: 'POR_UBICACION'
  })
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  const deportes = [
    { value: 'FUTBOL', label: 'Fútbol', emoji: '⚽', jugadores: [10, 22] },
    { value: 'BASQUET', label: 'Básquet', emoji: '🏀', jugadores: [8, 10] },
    { value: 'VOLEY', label: 'Vóley', emoji: '🏐', jugadores: [6, 12] }
  ]

  const niveles = [
    { value: 'PRINCIPIANTE', label: 'Principiante' },
    { value: 'INTERMEDIO', label: 'Intermedio' },
    { value: 'AVANZADO', label: 'Avanzado' }
  ]

  const estrategias = [
    { value: 'POR_UBICACION', label: 'Por Ubicación', description: 'Jugadores cercanos al lugar del partido' },
    { value: 'POR_NIVEL', label: 'Por Nivel', description: 'Jugadores con el mismo nivel de habilidad' },
    { value: 'ALEATORIO', label: 'Aleatorio', description: 'Selección aleatoria de jugadores disponibles' }
  ]

  const handleChange = (e) => {
    const { name, value } = e.target
    
    if (name.startsWith('ubicacion.')) {
      const field = name.split('.')[1]
      setFormData(prev => ({
        ...prev,
        ubicacion: {
          ...prev.ubicacion,
          [field]: value
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

  const validateForm = () => {
    if (!formData.deporte) {
      setError('Selecciona un deporte')
      return false
    }
    if (!formData.cantidadJugadores || formData.cantidadJugadores < 2) {
      setError('La cantidad de jugadores debe ser al menos 2')
      return false
    }
    if (!formData.duracion || formData.duracion < 30) {
      setError('La duración debe ser al menos 30 minutos')
      return false
    }
    if (!formData.ubicacion.direccion) {
      setError('Ingresa la dirección del partido')
      return false
    }
    if (!formData.horario) {
      setError('Selecciona fecha y hora del partido')
      return false
    }
    if (new Date(formData.horario) <= new Date()) {
      setError('La fecha y hora debe ser futura')
      return false
    }
    if (!formData.nivel) {
      setError('Selecciona el nivel requerido')
      return false
    }
    return true
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    
    if (!validateForm()) return

    try {
      const partidoData = {
        ...formData,
        cantidadJugadores: parseInt(formData.cantidadJugadores),
        duracion: parseInt(formData.duracion),
        ubicacion: {
          ...formData.ubicacion,
          latitud: parseFloat(formData.ubicacion.latitud) || -34.6037,
          longitud: parseFloat(formData.ubicacion.longitud) || -58.3816
        }
      }

      const result = await crearPartido(partidoData)
      
      if (result.success) {
        setSuccess('¡Partido creado exitosamente!')
        setTimeout(() => {
          navigate(`/partido/${result.partido.id}`)
        }, 2000)
      } else {
        setError(result.error || 'Error al crear el partido')
      }
    } catch (err) {
      setError('Error inesperado. Intenta nuevamente.')
    }
  }

  const deporteSeleccionado = deportes.find(d => d.value === formData.deporte)

  return (
    <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Crear Nuevo Partido</h1>
        <p className="text-gray-600 mt-2">
          Organiza un partido deportivo y encuentra jugadores automáticamente
        </p>
      </div>

      <div className="card">
        <div className="card-body">
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

          <form onSubmit={handleSubmit} className="space-y-8">
            {/* Información Básica */}
            <div className="space-y-6">
              <h2 className="text-xl font-semibold text-gray-900 border-b border-gray-200 pb-2">
                Información Básica
              </h2>

              <div className="grid md:grid-cols-2 gap-6">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-3">
                    Deporte
                  </label>
                  <div className="space-y-2">
                    {deportes.map(deporte => (
                      <label key={deporte.value} className="flex items-center space-x-3 p-3 border border-gray-200 rounded-lg hover:bg-gray-50 cursor-pointer">
                        <input
                          type="radio"
                          name="deporte"
                          value={deporte.value}
                          checked={formData.deporte === deporte.value}
                          onChange={handleChange}
                          className="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300"
                        />
                        <span className="text-lg">{deporte.emoji}</span>
                        <div className="flex-1">
                          <span className="text-sm font-medium text-gray-900">{deporte.label}</span>
                          <p className="text-xs text-gray-500">
                            {deporte.jugadores[0]}-{deporte.jugadores[1]} jugadores típicos
                          </p>
                        </div>
                      </label>
                    ))}
                  </div>
                </div>

                <div className="space-y-4">
                  <div>
                    <label htmlFor="cantidadJugadores" className="block text-sm font-medium text-gray-700 mb-1">
                      Cantidad de Jugadores
                    </label>
                    <div className="relative">
                      <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <Users className="h-5 w-5 text-gray-400" />
                      </div>
                      <input
                        id="cantidadJugadores"
                        name="cantidadJugadores"
                        type="number"
                        min="2"
                        max="30"
                        value={formData.cantidadJugadores}
                        onChange={handleChange}
                        className="input pl-10"
                        placeholder="Ej: 10"
                      />
                    </div>
                    {deporteSeleccionado && (
                      <p className="text-xs text-gray-500 mt-1">
                        Recomendado: {deporteSeleccionado.jugadores[0]}-{deporteSeleccionado.jugadores[1]} jugadores
                      </p>
                    )}
                  </div>

                  <div>
                    <label htmlFor="duracion" className="block text-sm font-medium text-gray-700 mb-1">
                      Duración (minutos)
                    </label>
                    <div className="relative">
                      <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <Clock className="h-5 w-5 text-gray-400" />
                      </div>
                      <input
                        id="duracion"
                        name="duracion"
                        type="number"
                        min="30"
                        max="180"
                        value={formData.duracion}
                        onChange={handleChange}
                        className="input pl-10"
                        placeholder="Ej: 90"
                      />
                    </div>
                  </div>

                  <div>
                    <label htmlFor="nivel" className="block text-sm font-medium text-gray-700 mb-1">
                      Nivel Requerido
                    </label>
                    <select
                      id="nivel"
                      name="nivel"
                      value={formData.nivel}
                      onChange={handleChange}
                      className="input"
                    >
                      <option value="">Seleccionar nivel</option>
                      {niveles.map(nivel => (
                        <option key={nivel.value} value={nivel.value}>
                          {nivel.label}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
              </div>
            </div>

            {/* Ubicación y Horario */}
            <div className="space-y-6">
              <h2 className="text-xl font-semibold text-gray-900 border-b border-gray-200 pb-2">
                Ubicación y Horario
              </h2>

              <div className="grid md:grid-cols-2 gap-6">
                <div className="space-y-4">
                  <div>
                    <label htmlFor="direccion" className="block text-sm font-medium text-gray-700 mb-1">
                      Dirección del Partido
                    </label>
                    <div className="relative">
                      <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <MapPin className="h-5 w-5 text-gray-400" />
                      </div>
                      <input
                        id="direccion"
                        name="ubicacion.direccion"
                        type="text"
                        value={formData.ubicacion.direccion}
                        onChange={handleChange}
                        className="input pl-10"
                        placeholder="Ej: Cancha Municipal, Buenos Aires"
                      />
                    </div>
                  </div>

                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <label htmlFor="latitud" className="block text-sm font-medium text-gray-700 mb-1">
                        Latitud (opcional)
                      </label>
                      <input
                        id="latitud"
                        name="ubicacion.latitud"
                        type="number"
                        step="any"
                        value={formData.ubicacion.latitud}
                        onChange={handleChange}
                        className="input"
                        placeholder="-34.6037"
                      />
                    </div>
                    <div>
                      <label htmlFor="longitud" className="block text-sm font-medium text-gray-700 mb-1">
                        Longitud (opcional)
                      </label>
                      <input
                        id="longitud"
                        name="ubicacion.longitud"
                        type="number"
                        step="any"
                        value={formData.ubicacion.longitud}
                        onChange={handleChange}
                        className="input"
                        placeholder="-58.3816"
                      />
                    </div>
                  </div>
                </div>

                <div>
                  <label htmlFor="horario" className="block text-sm font-medium text-gray-700 mb-1">
                    Fecha y Hora
                  </label>
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <Calendar className="h-5 w-5 text-gray-400" />
                    </div>
                    <input
                      id="horario"
                      name="horario"
                      type="datetime-local"
                      value={formData.horario}
                      onChange={handleChange}
                      className="input pl-10"
                      min={new Date().toISOString().slice(0, 16)}
                    />
                  </div>
                </div>
              </div>
            </div>

            {/* Estrategia de Emparejamiento */}
            <div className="space-y-6">
              <h2 className="text-xl font-semibold text-gray-900 border-b border-gray-200 pb-2">
                Estrategia de Emparejamiento
              </h2>

              <div className="space-y-3">
                {estrategias.map(estrategia => (
                  <label key={estrategia.value} className="flex items-start space-x-3 p-4 border border-gray-200 rounded-lg hover:bg-gray-50 cursor-pointer">
                    <input
                      type="radio"
                      name="emparejamiento"
                      value={estrategia.value}
                      checked={formData.emparejamiento === estrategia.value}
                      onChange={handleChange}
                      className="h-4 w-4 text-primary-600 focus:ring-primary-500 border-gray-300 mt-1"
                    />
                    <div className="flex-1">
                      <span className="text-sm font-medium text-gray-900">{estrategia.label}</span>
                      <p className="text-xs text-gray-500 mt-1">{estrategia.description}</p>
                    </div>
                  </label>
                ))}
              </div>
            </div>

            {/* Preview */}
            {formData.deporte && formData.nivel && (
              <div className="bg-gray-50 rounded-lg p-6">
                <h3 className="text-lg font-medium text-gray-900 mb-4">Vista Previa del Partido</h3>
                <div className="flex flex-wrap items-center gap-3">
                  <DeporteBadge deporte={formData.deporte} size="lg" />
                  <NivelBadge nivel={formData.nivel} size="lg" />
                  {formData.cantidadJugadores && (
                    <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-blue-100 text-blue-800">
                      <Users size={14} className="mr-1" />
                      {formData.cantidadJugadores} jugadores
                    </span>
                  )}
                  {formData.duracion && (
                    <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-purple-100 text-purple-800">
                      <Clock size={14} className="mr-1" />
                      {formData.duracion} min
                    </span>
                  )}
                </div>
              </div>
            )}

            {/* Botones */}
            <div className="flex flex-col sm:flex-row gap-4 pt-6 border-t border-gray-200">
              <button
                type="button"
                onClick={() => navigate('/dashboard')}
                className="btn-secondary flex-1 sm:flex-none"
              >
                Cancelar
              </button>
              <button
                type="submit"
                disabled={loading}
                className="btn-primary flex-1"
              >
                {loading ? (
                  <div className="flex items-center justify-center space-x-2">
                    <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                    <span>Creando partido...</span>
                  </div>
                ) : (
                  'Crear Partido'
                )}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default CrearPartido