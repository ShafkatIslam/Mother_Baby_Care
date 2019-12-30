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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mother`
--
ALTER TABLE `mother`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mother`
--
ALTER TABLE `mother`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
