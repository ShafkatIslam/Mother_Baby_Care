-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 11, 2019 at 11:46 PM
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
<div class="error"><h1>Error</h1><p><strong>SQL query:</strong>
<a href="db_sql.php?sql_query=SET+SQL_QUOTE_SHOW_CREATE+%3D+1&amp;show_query=1&amp;db=tikabarta_baby_mother"><span class="nowrap"><img src="themes/dot.gif" title="Edit" alt="Edit" class="icon ic_b_edit" />&nbsp;Edit</span></a>    </p>
<p>
<code class="sql"><pre>
SET SQL_QUOTE_SHOW_CREATE = 1
</pre></code>
</p>
<p>
    <strong>MySQL said: </strong><a href="./url.php?url=https%3A%2F%2Fdev.mysql.com%2Fdoc%2Frefman%2F5.7%2Fen%2Ferror-messages-server.html" target="mysql_doc"><img src="themes/dot.gif" title="Documentation" alt="Documentation" class="icon ic_b_help" /></a>
</p>
<code>#2006 - MySQL server has gone away</code><br/></div>