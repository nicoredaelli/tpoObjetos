import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { usePartidos } from '../contexts/PartidoContext'
import { 
  Calendar, 
  MapPin, 
  Users, 
  Clock,
  Filter,
  Search,
  Trophy
} from 'lucide-react'
import { format } from 'date-fns'
import { es } from 'date-fns/locale'
import EstadoBadge from '../components/EstadoBadge'
import DeporteBadge from '../components/DeporteBadge'
import NivelBadge from '../components/NivelBadge'

const MisPartidos = () => {
  const { obtenerMisPartidos } = usePartidos()
  const [filtroEstado, setFiltroEstado] = useState('TODOS')
  const [filtroDeporte, setFiltroDeporte] = useState('TODOS')
  const [busqueda, setBusqueda] = useState('')
  
  const misPartidos = obtenerMisPartidos()

  const estados = [
    { value: 'TODOS', label: 'Todos los Estados' },
    { value: 'NECESITAMOS_JUGADORES', label: 'Necesitamos Jugadores' },
    { value: 'PARTIDO_ARMADO', label: 'Partido Armado' },
    { value: 'CONFIRMACION', label: 'Esperando Confirmación' },
    { value: 'EN_JUEGO', label: 'En Juego' },
    { value: 'FINALIZADO', label: 'Finalizado' },
    { value: 'CANCELADO', label: 'Cancelado' }
  ]

  const deportes = [
    { value: 'TODOS', label: 'Todos los Deportes' },
    { value: 'FUTBOL', label: 'Fútbol' },
    { value: 'BASQUET', label: 'Básquet' },
    { value: 'VOLEY', label: 'Vóley' }
  ]

  const partidosFiltrados = misPartidos.filter(partido => {
    const cumpleFiltroEstado = filtroEstado === 'TODOS' || partido.estado === filtroEstado
    const cumpleFiltroDeporte = filtroDeporte === 'TODOS' || partido.deporte === filtroDeporte
    const cumpleBusqueda = busqueda === '' || 
      partido.ubicacion.direccion.toLowerCase().includes(busqueda.toLowerCase()) ||
      partido.deporte.toLowerCase().includes(busqueda.toLowerCase())
    
    return cumpleFiltroEstado && cumpleFiltroDeporte && cumpleBusqueda
  })

  const partidosActivos = partidosFiltrados.filter(p => 
    ['NECESITAMOS_JUGADORES', 'PARTIDO_ARMADO', 'CONFIRMACION', 'EN_JUEGO'].includes(p.estado)
  )
  
  const partidosFinalizados = partidosFiltrados.filter(p => 
    ['FINALIZADO', 'CANCELADO'].includes(p.estado)
  )

  const PartidoCard = ({ partido }) => (
    <Link
      to={`/partido/${partido.id}`}
      className="card hover:shadow-medium transition-all duration-300 group"
    >
      <div className="card-body">
        <div className="flex items-start justify-between mb-4">
          <div className="flex items-center space-x-3">
            <DeporteBadge deporte={partido.deporte} />
            <NivelBadge nivel={partido.nivel} />
          </div>
          <EstadoBadge estado={partido.estado} />
        </div>

        <div className="space-y-3">
          <div className="flex items-center space-x-2 text-gray-600">
            <Calendar size={16} />
            <span className="text-sm">
              {format(new Date(partido.horario), 'PPP p', { locale: es })}
            </span>
          </div>

          <div className="flex items-center space-x-2 text-gray-600">
            <MapPin size={16} />
            <span className="text-sm">{partido.ubicacion.direccion}</span>
          </div>

          <div className="flex items-center justify-between">
            <div className="flex items-center space-x-2 text-gray-600">
              <Users size={16} />
              <span className="text-sm">
                {partido.jugadoresParticipan.length}/{partido.cantidadJugadores} jugadores
              </span>
            </div>
            <div className="flex items-center space-x-2 text-gray-600">
              <Clock size={16} />
              <span className="text-sm">{partido.duracion} min</span>
            </div>
          </div>

          {partido.estado === 'CONFIRMACION' && (
            <div className="bg-warning-50 border border-warning-200 rounded-lg p-3">
              <p className="text-sm text-warning-700">
                Confirmaciones: {partido.confirmaciones}/{partido.jugadoresParticipan.length}
              </p>
            </div>
          )}
        </div>
      </div>
    </Link>
  )

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Mis Partidos</h1>
        <p className="text-gray-600 mt-2">
          Gestiona todos tus partidos deportivos
        </p>
      </div>

      {/* Filtros y Búsqueda */}
      <div className="card mb-8">
        <div className="card-body">
          <div className="flex flex-col lg:flex-row gap-4">
            <div className="flex-1">
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <Search className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  type="text"
                  placeholder="Buscar por ubicación o deporte..."
                  value={busqueda}
                  onChange={(e) => setBusqueda(e.target.value)}
                  className="input pl-10"
                />
              </div>
            </div>
            
            <div className="flex flex-col sm:flex-row gap-4">
              <div className="relative">
                <Filter className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
                <select
                  value={filtroEstado}
                  onChange={(e) => setFiltroEstado(e.target.value)}
                  className="input pl-10 pr-8"
                >
                  {estados.map(estado => (
                    <option key={estado.value} value={estado.value}>
                      {estado.label}
                    </option>
                  ))}
                </select>
              </div>

              <select
                value={filtroDeporte}
                onChange={(e) => setFiltroDeporte(e.target.value)}
                className="input"
              >
                {deportes.map(deporte => (
                  <option key={deporte.value} value={deporte.value}>
                    {deporte.label}
                  </option>
                ))}
              </select>
            </div>
          </div>
        </div>
      </div>

      {/* Estadísticas */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <div className="card">
          <div className="card-body text-center">
            <div className="text-3xl font-bold text-primary-600 mb-2">
              {misPartidos.length}
            </div>
            <div className="text-gray-600">Total de Partidos</div>
          </div>
        </div>
        
        <div className="card">
          <div className="card-body text-center">
            <div className="text-3xl font-bold text-success-600 mb-2">
              {partidosActivos.length}
            </div>
            <div className="text-gray-600">Partidos Activos</div>
          </div>
        </div>
        
        <div className="card">
          <div className="card-body text-center">
            <div className="text-3xl font-bold text-gray-600 mb-2">
              {partidosFinalizados.length}
            </div>
            <div className="text-gray-600">Partidos Finalizados</div>
          </div>
        </div>
      </div>

      {partidosFiltrados.length === 0 ? (
        <div className="card">
          <div className="card-body text-center py-12">
            <Trophy className="mx-auto h-12 w-12 text-gray-400 mb-4" />
            <h3 className="text-lg font-medium text-gray-900 mb-2">
              No se encontraron partidos
            </h3>
            <p className="text-gray-500 mb-6">
              {misPartidos.length === 0 
                ? 'Aún no tienes partidos. ¡Crea tu primer partido!'
                : 'Intenta ajustar los filtros de búsqueda.'
              }
            </p>
            {misPartidos.length === 0 && (
              <Link to="/crear-partido" className="btn-primary">
                Crear Primer Partido
              </Link>
            )}
          </div>
        </div>
      ) : (
        <div className="space-y-8">
          {/* Partidos Activos */}
          {partidosActivos.length > 0 && (
            <div>
              <h2 className="text-xl font-semibold text-gray-900 mb-4">
                Partidos Activos ({partidosActivos.length})
              </h2>
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {partidosActivos.map(partido => (
                  <PartidoCard key={partido.id} partido={partido} />
                ))}
              </div>
            </div>
          )}

          {/* Partidos Finalizados */}
          {partidosFinalizados.length > 0 && (
            <div>
              <h2 className="text-xl font-semibold text-gray-900 mb-4">
                Historial ({partidosFinalizados.length})
              </h2>
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {partidosFinalizados.map(partido => (
                  <PartidoCard key={partido.id} partido={partido} />
                ))}
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  )
}

export default MisPartidos