-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 17, 2019 at 06:16 PM
-- Server version: 10.1.37-MariaDB-cll-lve
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tikabarta_baby_mother`
--

-- --------------------------------------------------------

--
-- Table structure for table `vaccine`
--

CREATE TABLE `vaccine` (
  `id` int(11) NOT NULL,
  `cell` varchar(255) NOT NULL,
  `pdate` date NOT NULL,
  `ndate` date NOT NULL,
  `number` int(11) NOT NULL,
  `numbers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vaccine`
--

INSERT INTO `vaccine` (`id`, `cell`, `pdate`, `ndate`, `number`, `numbers`) VALUES
(4, '1234', '2018-11-18', '2018-11-18', 2, 1),
(5, '1234', '2018-11-20', '2019-05-21', 3, 2),
(6, '1234', '2019-05-22', '2020-05-21', 4, 2),
(7, '1234', '2020-05-22', '2021-05-21', 5, 2),
(8, '1245', '2018-12-08', '2018-12-08', 3, 1),
(9, '1245', '2019-01-03', '2020-01-02', 4, 2),
(11, '1234', '2018-11-01', '2018-11-01', 1, 3),
(12, '1234', '2021-07-01', '0000-00-00', 6, 2),
(13, '1245', '2018-08-01', '2018-08-01', 2, 3),
(15, '1245', '2018-08-15', '2018-08-15', 1, 3),
(18, '1245', '2020-03-02', '2021-03-01', 5, 2),
(19, '1245', '2021-03-02', '0000-00-00', 6, 2),
(20, '1020', '2018-12-27', '2018-12-27', 2, 1),
(21, '1020', '2018-12-28', '2019-06-28', 3, 2),
(22, '1020', '2018-12-01', '2018-12-01', 1, 3),
(23, '1020', '2019-06-29', '2020-06-29', 4, 2),
(24, '1020', '2020-06-29', '2021-06-29', 5, 2),
(25, '123', '2019-01-15', '2019-01-15', 2, 1),
(26, '123', '2019-01-21', '2019-07-23', 3, 2),
(27, '123', '2019-07-23', '2020-07-22', 4, 2),
(28, '123', '2020-07-22', '2021-07-21', 5, 2),
(29, '123', '2021-07-21', '0000-00-00', 6, 2),
(30, '123', '2019-01-01', '2019-01-01', 1, 3),
(31, '121', '2019-01-16', '2019-01-18', 2, 1),
(32, '121', '2019-01-18', '2019-07-19', 3, 2),
(33, '121', '2019-01-01', '2019-01-01', 1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `vaccine`
--
ALTER TABLE `vaccine`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `vaccine`
--
ALTER TABLE `vaccine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
