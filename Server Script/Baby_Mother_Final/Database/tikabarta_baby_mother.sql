-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 17, 2019 at 06:12 PM
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
-- Table structure for table `baby`
--

CREATE TABLE `baby` (
  `id` int(11) NOT NULL,
  `B_Name` varchar(255) NOT NULL,
  `BM_Email` varchar(255) NOT NULL,
  `B_User` varchar(255) NOT NULL,
  `BF_Cell` varchar(255) NOT NULL,
  `B_Pass` varchar(255) NOT NULL,
  `B_Day` date NOT NULL,
  `B_Gender` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `baby`
--

INSERT INTO `baby` (`id`, `B_Name`, `BM_Email`, `B_User`, `BF_Cell`, `B_Pass`, `B_Day`, `B_Gender`) VALUES
(7, 'Romana', 'Rahima', 'romana', '1003', '1003', '2018-12-01', 'Female');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `cell` varchar(255) NOT NULL,
  `pdate` date NOT NULL,
  `ndate` date NOT NULL,
  `number` int(11) NOT NULL,
  `numbers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `cell`, `pdate`, `ndate`, `number`, `numbers`) VALUES
(19, '1234', '2018-11-17', '2018-11-17', 2, 1),
(20, '1234', '2018-11-17', '2019-01-27', 3, 2),
(21, '1234', '2019-01-27', '2019-03-01', 4, 2),
(22, '1234', '2019-12-28', '2020-01-18', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `mother`
--

CREATE TABLE `mother` (
  `id` int(11) NOT NULL,
  `M_Name` varchar(255) NOT NULL,
  `M_Email` varchar(255) NOT NULL,
  `M_Cell` varchar(255) NOT NULL,
  `M_User` varchar(255) NOT NULL,
  `M_Blood` varchar(255) NOT NULL,
  `M_Week` int(11) NOT NULL,
  `M_Pass` varchar(255) NOT NULL,
  `M_Date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mother`
--

INSERT INTO `mother` (`id`, `M_Name`, `M_Email`, `M_Cell`, `M_User`, `M_Blood`, `M_Week`, `M_Pass`, `M_Date`) VALUES
(1, 'Rahima', 'rahima@gmail.com', '1234', 'rahima', 'A+', 1, '1234', '726814'),
(2, 'Karima', 'karima', '1245', 'karima', 'B+', 3, '1245', '726826'),
(3, 'Jamila', 'jamila@gmail.com', '1020', 'jamila', 'O+', 3, '1020', '726866'),
(4, 'Karisa', 'karisa', '123', 'karisa', 'B+', 4, '123', '726882'),
(5, 'Mina', 'mina@gmail.com', '121', 'Mina', 'B+', 2, '121', '726886');

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
-- Indexes for table `baby`
--
ALTER TABLE `baby`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mother`
--
ALTER TABLE `mother`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vaccine`
--
ALTER TABLE `vaccine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vaccine_baby`
--
ALTER TABLE `vaccine_baby`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `baby`
--
ALTER TABLE `baby`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `mother`
--
ALTER TABLE `mother`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `vaccine`
--
ALTER TABLE `vaccine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `vaccine_baby`
--
ALTER TABLE `vaccine_baby`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
