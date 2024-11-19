-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 19-11-2024 a las 04:10:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `yenny`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrega`
--

CREATE TABLE `entrega` (
  `id` int(11) NOT NULL,
  `contenido` text DEFAULT NULL,
  `estado` varchar(50) DEFAULT 'En revision',
  `feedback` text DEFAULT 'No hay feedback para esta entrega todavia',
  `fechaEntrega` date DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `libro_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrega`
--

INSERT INTO `entrega` (`id`, `contenido`, `estado`, `feedback`, `fechaEntrega`, `usuario_id`, `libro_id`) VALUES
(1, 'dsfsdf', 'En revision', 'No hay feedback para esta entrega todavia', '2024-10-29', 1, 1),
(2, 'dsfsdf', 'En revision', 'No hay feedback para esta entrega todavia', '2024-10-29', 1, 1),
(3, 'dsfsdf', 'En revision', 'No hay feedback para esta entrega todavia', '2024-10-29', 1, 1),
(4, 'dsfsdf', 'En revision', 'No hay feedback para esta entrega todavia', '2024-10-29', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `interes_literario`
--

CREATE TABLE `interes_literario` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `tipo_interes` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `autor_id` int(11) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `formato` varchar(50) DEFAULT NULL,
  `estadoLibro` varchar(50) DEFAULT NULL,
  `fechaLanzamiento` date DEFAULT NULL,
  `numeroVentas` int(11) DEFAULT 0,
  `stockDisponible` int(11) DEFAULT 0,
  `editor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id`, `titulo`, `autor_id`, `isbn`, `genero`, `precio`, `formato`, `estadoLibro`, `fechaLanzamiento`, `numeroVentas`, `stockDisponible`, `editor_id`) VALUES
(1, 'El señor de los anillos', 1, NULL, 'Fantasia', NULL, NULL, 'En Proceso', NULL, NULL, NULL, 3),
(4, 'BLA BLA', 1, NULL, 'Literatura Clásica', 0, '', 'En proceso', NULL, 0, 0, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `mail` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(20) DEFAULT 'autor',
  `estado_cuenta` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `mail`, `password`, `rol`, `estado_cuenta`) VALUES
(1, 'Franco', 'Aparicio', 'francomaty07@gmail.com', 'river123', 'autor', 'activo'),
(2, 'Franco', 'Aparicio', 'francomaty007@gmail.com', 'Hola1234', 'autor', 'activo'),
(3, 'Exequiel', 'Palacios', 'exequiel@davinci.com', 'HomeroSimpson1234', 'autor', 'activo'),
(4, 'Gonzalo', 'Fernandez', 'gonzafer@gmail.com', 'boca912', 'editor', 'activo'),
(5, 'Mauricio', 'Matos', 'mau@gmail.com', 'Mau123', 'admin', 'activo'),
(6, 'Florencia', 'Gonzalez', 'flor@gmail.com', 'Flor123', 'autor', 'activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entrega`
--
ALTER TABLE `entrega`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `libro_id` (`libro_id`);

--
-- Indices de la tabla `interes_literario`
--
ALTER TABLE `interes_literario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`),
  ADD KEY `autor_id` (`autor_id`),
  ADD KEY `editor_id` (`editor_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entrega`
--
ALTER TABLE `entrega`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `interes_literario`
--
ALTER TABLE `interes_literario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrega`
--
ALTER TABLE `entrega`
  ADD CONSTRAINT `libro_id` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`),
  ADD CONSTRAINT `usuario_id` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `interes_literario`
--
ALTER TABLE `interes_literario`
  ADD CONSTRAINT `interes_literario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`autor_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `libro_ibfk_2` FOREIGN KEY (`editor_id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
