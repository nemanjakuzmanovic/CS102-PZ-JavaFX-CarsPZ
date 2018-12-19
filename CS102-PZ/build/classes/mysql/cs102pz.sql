-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2017 at 07:43 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs102pz`
--

-- --------------------------------------------------------

--
-- Table structure for table `polovni`
--

CREATE TABLE `polovni` (
  `id` int(11) NOT NULL,
  `imgUrl` varchar(250) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `godiste` int(10) NOT NULL,
  `cena` int(30) NOT NULL,
  `url` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `polovni`
--

INSERT INTO `polovni` (`id`, `imgUrl`, `naziv`, `godiste`, `cena`, `url`) VALUES
(1, 'https://images3.polovniautomobili.com/user-images/classifieds/805/8050000/b092475d3473-144x108.jpg', 'Honda karavan super B', 2006, 5000, 'https://www.polovniautomobili.com/putnicka-vozila/10332462/volkswagen-touareg'),
(2, 'https://images3.polovniautomobili.com/user-images/classifieds/1021/10215895/5d179f87eacc-125x100.jpg', 'Opel Astra G 2.0 dti njoy', 2003, 2000, 'https://beta.polovniautomobili.com/putnicka-vozila/10215895/opel-astra-g-20-dti-njoy'),
(3, 'https://images3.polovniautomobili.com/user-images/classifieds/1035/10350153/2718942758b8-125x100.jpg', 'Mercedes Benz C 200', 2002, 3790, 'https://beta.polovniautomobili.com/putnicka-vozila/10350153/mercedes-benz-c-200-kao-nov'),
(4, 'https://images3.polovniautomobili.com/user-images/classifieds/1006/10067674/4aef76cf3956-125x100.jpg', 'Alfa Romeo 147 1.9 JTDm', 2005, 2999, 'https://beta.polovniautomobili.com/putnicka-vozila/10067674/alfa-romeo-147-19-jtdm'),
(5, 'https://images3.polovniautomobili.com/user-images/classifieds/1035/10350073/270db00e630d-125x100.jpg', 'Opel Astra G 1.7dti', 2004, 2690, 'https://beta.polovniautomobili.com/putnicka-vozila/10350073/opel-astra-g-17dti-55kw-nov-auto'),
(6, 'https://images3.polovniautomobili.com/user-images/classifieds/860/8608123/357484841eb3-125x100.jpg', 'Audi A6 V', 2007, 8600, 'https://beta.polovniautomobili.com/putnicka-vozila/8608123/audi-a6-vservis-garancija'),
(7, 'https://images3.polovniautomobili.com/user-images/classifieds/1018/10189062/89f5d6bbf0b8-125x100.jpg', 'Opel Astra G 2.0 DTI', 2003, 1950, 'https://beta.polovniautomobili.com/putnicka-vozila/10189062/opel-astra-g-20-dti');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(4, 'nomad', '123', 'nomad@nomad.com'),
(5, 'nomad25', 'vodavoda', 'nomadbrE@gmail.com'),
(6, 'nomad255', 'vodavoda', 'nomadbre@gmail.com'),
(7, 'admin', 'admin', 'admin@admin.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `polovni`
--
ALTER TABLE `polovni`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `polovni`
--
ALTER TABLE `polovni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
