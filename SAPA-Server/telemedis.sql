-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 25, 2012 at 09:54 
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `telemedis`
--

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `fileid` bigint(11) NOT NULL AUTO_INCREMENT,
  `filepath` varchar(100) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `filetype` varchar(100) NOT NULL,
  `filesize` bigint(20) NOT NULL,
  `filecategory` varchar(100) NOT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `file`
--

INSERT INTO `file` (`fileid`, `filepath`, `filename`, `filetype`, `filesize`, `filecategory`) VALUES
(1, 'android/Untitled.jpg', 'Untitled.jpg', 'jpg', 356806, 'tutorial'),
(2, 'android/Daftar Pembimbing KP D3 2012 for Mhs.pdf', 'Daftar Pembimbing KP D3 2012 for Mhs.pdf', 'pdf', 19436, 'tutorial'),
(3, 'android/Logbook.doc', 'Logbook.doc', 'doc', 237568, 'news'),
(4, 'android/Google Translate.MP3', 'Google Translate.MP3', 'mp3', 5257, 'library'),
(5, 'android/JTK 2 APPL1Teo (1st Task - Laporan Ke-robust-an - 001).docx', 'JTK 2 APPL1Teo (1st Task - Laporan Ke-robust-an - 001).docx', 'docx', 381940, 'news'),
(6, 'android/JTK 2 APPL1Teo (1st Task - Laporan Ke-robust-an - Fiyyan).txt', 'JTK 2 APPL1Teo (1st Task - Laporan Ke-robust-an - Fiyyan).txt', 'txt', 1020, 'library'),
(7, 'android/Future Vision of Healthcare - YouTube.3gp', 'Future Vision of Healthcare - YouTube.3gp', '3gp', 1515365, 'tutorial'),
(8, 'android/13032012409.jpg', '13032012409.jpg', 'jpg', 798730, 'news'),
(9, 'android/audio_Google_Translate.MP3', 'audio_Google_Translate.MP3', 'mp3', 5257, 'library'),
(10, 'android/Daftar_Pembimbing_KP_D3_2012_for_Mhs.pdf', 'Daftar_Pembimbing_KP_D3_2012_for_Mhs.pdf', 'pdf', 19436, 'tutorial'),
(11, 'android/Future_Vision_of_Healthcare_-_YouTube.3gp', 'Future_Vision_of_Healthcare_-_YouTube.3gp', '3gp', 1515365, 'library');

-- --------------------------------------------------------

--
-- Table structure for table `kader`
--

CREATE TABLE IF NOT EXISTS `kader` (
  `idPosyandu` int(11) NOT NULL,
  `totalKader` int(11) DEFAULT NULL,
  `kaderTerlatih` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kader`
--

INSERT INTO `kader` (`idPosyandu`, `totalKader`, `kaderTerlatih`) VALUES
(1, 15, 15),
(2, 20, 20);

-- --------------------------------------------------------

--
-- Table structure for table `keg_pkp_kec`
--

CREATE TABLE IF NOT EXISTS `keg_pkp_kec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kecamatan` varchar(1000) NOT NULL,
  `bulan` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `urutan_kegiatan` longtext NOT NULL,
  `tempat` varchar(1000) NOT NULL,
  `unsur_terlibat` longtext NOT NULL,
  `masalah_yang_dihadapi` longtext NOT NULL,
  `rtl` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `keg_pkp_kec`
--

INSERT INTO `keg_pkp_kec` (`id`, `kecamatan`, `bulan`, `tanggal`, `urutan_kegiatan`, `tempat`, `unsur_terlibat`, `masalah_yang_dihadapi`, `rtl`) VALUES
(4, 'Cimahi Tengah', '05', '0000-00-00', 'Membuat insert ke database melalui android', 'Lab BasDat ITB', 'Keukeu, Laras', 'Perancangan database, pembuatan form untuk laporan pengembangan posyandu, insert karena java.lang,null.pointerexception', '1');

-- --------------------------------------------------------

--
-- Table structure for table `perkembangan_posyandu`
--

CREATE TABLE IF NOT EXISTS `perkembangan_posyandu` (
  `idPosyandu` int(11) NOT NULL AUTO_INCREMENT,
  `kecamatan` varchar(100) DEFAULT NULL,
  `desa` varchar(100) DEFAULT NULL,
  `kelurahan` varchar(100) NOT NULL,
  `jmlBangunan` int(11) DEFAULT NULL,
  `s` int(11) DEFAULT NULL,
  `k` int(11) DEFAULT NULL,
  `d` int(11) DEFAULT NULL,
  `n` int(11) DEFAULT NULL,
  `progPengembangan` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPosyandu`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `perkembangan_posyandu`
--

INSERT INTO `perkembangan_posyandu` (`idPosyandu`, `kecamatan`, `desa`, `kelurahan`, `jmlBangunan`, `s`, `k`, `d`, `n`, `progPengembangan`) VALUES
(1, 'Lembang', 'Gudang Kahuripan', 'Gudang Kahuripan', 10, 10, 10, 10, 10, 10),
(2, 'Cimahi tengah', 'Baros', 'Baros', 2, 10, 15, 20, 25, 30);

-- --------------------------------------------------------

--
-- Table structure for table `program_pokok`
--

CREATE TABLE IF NOT EXISTS `program_pokok` (
  `idPosyandu` int(11) NOT NULL,
  `kb` int(11) DEFAULT NULL,
  `kia` int(11) DEFAULT NULL,
  `gizi` int(11) DEFAULT NULL,
  `imunisasi` int(11) DEFAULT NULL,
  `p2d` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_pokok`
--

INSERT INTO `program_pokok` (`idPosyandu`, `kb`, `kia`, `gizi`, `imunisasi`, `p2d`) VALUES
(1, 10, 12, 14, 16, 18);

-- --------------------------------------------------------

--
-- Table structure for table `strata_posyandu`
--

CREATE TABLE IF NOT EXISTS `strata_posyandu` (
  `idPosyandu` int(11) NOT NULL,
  `pratama` int(11) DEFAULT NULL,
  `madya` int(11) DEFAULT NULL,
  `purnama` int(11) DEFAULT NULL,
  `mandiri` int(11) DEFAULT NULL,
  `jumlahStrata` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `strata_posyandu`
--

INSERT INTO `strata_posyandu` (`idPosyandu`, `pratama`, `madya`, `purnama`, `mandiri`, `jumlahStrata`) VALUES
(1, 5, 5, 5, 5, 20),
(2, 5, 10, 15, 20, 50);

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`username`, `password`, `email`, `status`) VALUES
('keukeu', 'android', 'keukeuanggaraniputri@gmail.com', 1),
('laras', 'laras', 'laraservintyana@gmail.com', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
