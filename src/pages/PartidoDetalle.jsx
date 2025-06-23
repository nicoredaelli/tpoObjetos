import React, { useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext'
import { usePartidos } from '../contexts/PartidoContext'
import { 
  MapPin, 
  Clock, 
  Users, 
  Calendar,
  User,
  CheckCircle,
  XCircle,
  Play,
  Trophy,
  ArrowLeft,
  ExternalLink,
  AlertCircle
} from 'lucide-react'
import { format } from 'date-fns'
import { es } from 'date-fns/locale'
import EstadoBadge from '../components/EstadoBadge'
import DeporteBadge from '../components/DeporteBadge'
import NivelBadge from '../components/NivelBadge'

const PartidoDetalle = () => {
  const { id } = useParams()
  const { user } = useAuth()
  const { 
    obtenerPartido, 
    unirsePartido, 
    salirPartido, 
    confirmarParticipacion,
    cambiarEstadoPartido,
    loading 
  } = usePartidos()
  const navigate = useNavigate()
  
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  
  const partido = obtenerPartido(id)

  if (!partido) {
    return (
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="card">
          <div className="card-body text-center py-12">
            <AlertCircle className="mx-auto h-12 w-12 text-gray-400 mb-4" />
            <h3 className="text-lg font-medium text-gray-900 mb-2">
              Partido no encontrado
            </h3>
            <p className="text-gray-500 mb-6">
              El partido que buscas no existe o ha sido eliminado.
            </p>
            <button onClick={() => navigate('/dashboard')} className="btn-primary">
              Volver al Dashboard
            </button>
          </div>
        </div>
      </div>
    )
  }

  const esCreador = user?.id === partido.creadorUsuario.id
  const estaParticipando = partido.jugadoresParticipan.some(j => j.id === user?.id)
  const puedeUnirse = !estaParticipando && 
    partido.estado === 'NECESITAMOS_JUGADORES' && 
    partido.jugadoresParticipan.length < partido.cantidadJugadores

  const handleUnirse = async () => {
    try {
      const result = await unirsePartido(partido.id)
      if (result.success) {
        setSuccess('¡Te has unido al partido exitosamente!')
        setError('')
      } else {
        setError(result.error || 'Error al unirse al partido')
      }
    } catch (err) {
      setError('Error inesperado')
    }
  }

  const handleSalir = async () => {
    if (esCreador) {
      setError('Como creador del partido, no puedes salir. Puedes cancelar el partido.')
      return
    }
    
    try {
      const result = await salirPartido(partido.id)
      if (result.success) {
        setSuccess('Has salido del partido')
        setError('')
      } else {
        setError(result.error || 'Error al salir del partido')
      }
    } catch (err) {
      setError('Error inesperado')
    }
  }

  const handleConfirmar = async () => {
    try {
      const result = await confirmarParticipacion(partido.id)
      if (result.success) {
        setSuccess('Participación confirmada')
        setError('')
      } else {
        setError(result.error || 'Error al confirmar participación')
      }
    } catch (err) {
      setError('Error inesperado')
    }
  }

  const handleCambiarEstado = async (nuevoEstado) => {
    try {
      const result = await cambiarEstadoPartido(partido.id, nuevoEstado)
      if (result.success) {
        setSuccess(`Estado cambiado a ${nuevoEstado}`)
        setError('')
      } else {
        setError(result.error || 'Error al cambiar estado')
      }
    } catch (err) {
      setError('Error inesperado')
    }
  }

  const getGoogleMapsUrl = () => {
    const { latitud, longitud, direccion } = partido.ubicacion
    if (latitud && longitud) {
      return `https://www.google.com/maps?q=${latitud},${longitud}`
    }
    return `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(direccion)}`
  }

  return (
    <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      {/* Header */}
      <div className="mb-8">
        <button
          onClick={() => navigate(-1)}
          className="flex items-center space-x-2 text-gray-600 hover:text-gray-900 mb-4"
        >
          <ArrowLeft size={20} />
          <span>Volver</span>
        </button>
        
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between">
          <div>
            <h1 className="text-3xl font-bold text-gray-900">Detalle del Partido</h1>
            <p className="text-gray-600 mt-2">
              Creado por {partido.creadorUsuario.nombre}
            </p>
          </div>
          <div className="mt-4 sm:mt-0">
            <EstadoBadge estado={partido.estado} size="lg" />
          </div>
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
        {/* Información Principal */}
        <div className="lg:col-span-2 space-y-6">
          {/* Información Básica */}
          <div className="card">
            <div className="card-header">
              <h2 className="text-xl font-semibold text-gray-900">Información del Partido</h2>
            </div>
            <div className="card-body space-y-4">
              <div className="flex flex-wrap items-center gap-3">
                <DeporteBadge deporte={partido.deporte} size="lg" />
                <NivelBadge nivel={partido.nivel} size="lg" />
                <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-blue-100 text-blue-800">
                  <Clock size={14} className="mr-1" />
                  {partido.duracion} minutos
                </span>
              </div>

              <div className="grid md:grid-cols-2 gap-4">
                <div className="flex items-center space-x-3 text-gray-600">
                  <Calendar size={20} />
                  <div>
                    <p className="font-medium text-gray-900">Fecha y Hora</p>
                    <p className="text-sm">
                      {format(new Date(partido.horario), 'PPP p', { locale: es })}
                    </p>
                  </div>
                </div>

                <div className="flex items-center space-x-3 text-gray-600">
                  <Users size={20} />
                  <div>
                    <p className="font-medium text-gray-900">Jugadores</p>
                    <p className="text-sm">
                      {partido.jugadoresParticipan.length}/{partido.cantidadJugadores} confirmados
                    </p>
                  </div>
                </div>
              </div>

              <div className="flex items-start space-x-3 text-gray-600">
                <MapPin size={20} className="mt-1" />
                <div className="flex-1">
                  <p className="font-medium text-gray-900">Ubicación</p>
                  <p className="text-sm mb-2">{partido.ubicacion.direccion}</p>
                  <a
                    href={getGoogleMapsUrl()}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="inline-flex items-center space-x-1 text-primary-600 hover:text-primary-700 text-sm"
                  >
                    <span>Ver en Google Maps</span>
                    <ExternalLink size={14} />
                  </a>
                </div>
              </div>
            </div>
          </div>

          {/* Lista de Jugadores */}
          <div className="card">
            <div className="card-header">
              <h2 className="text-xl font-semibold text-gray-900">
                Jugadores Participantes ({partido.jugadoresParticipan.length})
              </h2>
            </div>
            <div className="card-body">
              <div className="space-y-3">
                {partido.jugadoresParticipan.map((jugador, index) => (
                  <div key={jugador.id} className="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
                    <div className="flex items-center space-x-3">
                      <div className="w-8 h-8 bg-primary-100 rounded-full flex items-center justify-center">
                        <User size={16} className="text-primary-600" />
                      </div>
                      <div>
                        <p className="font-medium text-gray-900">
                          {jugador.nombre}
                          {jugador.id === partido.creadorUsuario.id && (
                            <span className="ml-2 text-xs bg-primary-100 text-primary-800 px-2 py-1 rounded-full">
                              Creador
                            </span>
                          )}
                        </p>
                      </div>
                    </div>
                    {partido.estado === 'PARTIDO_ARMADO' && (
                      <div className="text-sm text-gray-500">
                        Esperando confirmación
                      </div>
                    )}
                  </div>
                ))}
                
                {/* Espacios vacíos */}
                {Array.from({ length: partido.cantidadJugadores - partido.jugadoresParticipan.length }).map((_, index) => (
                  <div key={`empty-${index}`} className="flex items-center space-x-3 p-3 border-2 border-dashed border-gray-300 rounded-lg">
                    <div className="w-8 h-8 bg-gray-200 rounded-full flex items-center justify-center">
                      <User size={16} className="text-gray-400" />
                    </div>
                    <p className="text-gray-500">Esperando jugador...</p>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>

        {/* Panel de Acciones */}
        <div className="space-y-6">
          {/* Acciones del Usuario */}
          <div className="card">
            <div className="card-header">
              <h3 className="text-lg font-semibold text-gray-900">Acciones</h3>
            </div>
            <div className="card-body space-y-3">
              {puedeUnirse && (
                <button
                  onClick={handleUnirse}
                  disabled={loading}
                  className="w-full btn-success"
                >
                  <Users size={16} className="mr-2" />
                  Unirse al Partido
                </button>
              )}

              {estaParticipando && !esCreador && partido.estado === 'NECESITAMOS_JUGADORES' && (
                <button
                  onClick={handleSalir}
                  disabled={loading}
                  className="w-full btn-error"
                >
                  <XCircle size={16} className="mr-2" />
                  Salir del Partido
                </button>
              )}

              {estaParticipando && partido.estado === 'PARTIDO_ARMADO' && (
                <button
                  onClick={handleConfirmar}
                  disabled={loading}
                  className="w-full btn-primary"
                >
                  <CheckCircle size={16} className="mr-2" />
                  Confirmar Participación
                </button>
              )}
            </div>
          </div>

          {/* Acciones del Creador */}
          {esCreador && (
            <div className="card">
              <div className="card-header">
                <h3 className="text-lg font-semibold text-gray-900">Gestión del Partido</h3>
              </div>
              <div className="card-body space-y-3">
                {partido.estado === 'CONFIRMACION' && (
                  <button
                    onClick={() => handleCambiarEstado('EN_JUEGO')}
                    disabled={loading}
                    className="w-full btn-success"
                  >
                    <Play size={16} className="mr-2" />
                    Comenzar Partido
                  </button>
                )}

                {partido.estado === 'EN_JUEGO' && (
                  <button
                    onClick={() => handleCambiarEstado('FINALIZADO')}
                    disabled={loading}
                    className="w-full btn-primary"
                  >
                    <Trophy size={16} className="mr-2" />
                    Finalizar Partido
                  </button>
                )}

                {!['FINALIZADO', 'CANCELADO'].includes(partido.estado) && (
                  <button
                    onClick={() => handleCambiarEstado('CANCELADO')}
                    disabled={loading}
                    className="w-full btn-error"
                  >
                    <XCircle size={16} className="mr-2" />
                    Cancelar Partido
                  </button>
                )}
              </div>
            </div>
          )}

          {/* Información Adicional */}
          <div className="card">
            <div className="card-header">
              <h3 className="text-lg font-semibold text-gray-900">Información</h3>
            </div>
            <div className="card-body space-y-3 text-sm">
              <div>
                <span className="font-medium text-gray-900">Emparejamiento:</span>
                <p className="text-gray-600 mt-1">
                  {partido.emparejamiento === 'POR_UBICACION' && 'Por Ubicación'}
                  {partido.emparejamiento === 'POR_NIVEL' && 'Por Nivel'}
                  {partido.emparejamiento === 'ALEATORIO' && 'Aleatorio'}
                </p>
              </div>
              
              {partido.estado === 'CONFIRMACION' && (
                <div>
                  <span className="font-medium text-gray-900">Confirmaciones:</span>
                  <p className="text-gray-600 mt-1">
                    {partido.confirmaciones}/{partido.jugadoresParticipan.length} jugadores han confirmado
                  </p>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default PartidoDetalle