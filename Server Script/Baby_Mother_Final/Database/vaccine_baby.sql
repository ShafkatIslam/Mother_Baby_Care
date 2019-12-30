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
-- Table structure for table `vaccine_baby`
--

CREATE TABLE `vaccine_baby` (
  `id` int(11) NOT NULL,
  `cell` varchar(255) NOT NULL,
  `pdate` date NOT NULL,
  `ndate` date NOT NULL,
  `number` int(11) NOT NULL,
  `numbers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vaccine_baby`
--

INSERT INTO `vaccine_baby` (`id`, `cell`, `pdate`, `ndate`, `number`, `numbers`) VALUES
(1, '1003', '2018-12-31', '2018-12-31', 2, 1),
(2, '1003', '2019-01-03', '2019-03-13', 3, 2),
(3, '1003', '2019-03-13', '2019-06-21', 4, 2),
(4, '1003', '2019-06-22', '2019-12-23', 5, 2),
(5, '1003', '2019-12-24', '2020-07-24', 6, 2),
(8, '1003', '2020-07-24', '2021-04-24', 7, 2),
(9, '1003', '2021-04-25', '2022-05-01', 8, 2),
(10, '1003', '2018-11-01', '2018-11-01', 1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `vaccine_baby`
--
ALTER TABLE `vaccine_baby`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `vaccine_baby`
--
ALTER TABLE `vaccine_baby`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
