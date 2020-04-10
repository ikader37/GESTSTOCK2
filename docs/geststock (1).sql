-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 10 Avril 2020 à 16:22
-- Version du serveur :  10.1.21-MariaDB
-- Version de PHP :  7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `geststock`
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `ID` int(11) NOT NULL,
  `CATEGORIE_ID` int(11) DEFAULT NULL,
  `DESIGNATION` varchar(254) DEFAULT NULL,
  `QUANTITE` decimal(8,0) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `CREATED_BY` varchar(254) DEFAULT NULL,
  `UPDATED_BY` varchar(254) DEFAULT NULL,
  `PRIX_UNITAIRE` decimal(8,0) DEFAULT NULL,
  `DATE_SORTIE` datetime DEFAULT NULL,
  `FOURNISSEUR_ID` int(11) DEFAULT NULL,
  `idrangemennt` int(11) DEFAULT NULL,
  `prix_vente` int(11) DEFAULT NULL,
  `tva` tinyint(1) DEFAULT '0',
  `qtite_min` int(11) DEFAULT NULL,
  `reference` varchar(50) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `articles`
--

INSERT INTO `articles` (`ID`, `CATEGORIE_ID`, `DESIGNATION`, `QUANTITE`, `updated_at`, `created_at`, `CREATED_BY`, `UPDATED_BY`, `PRIX_UNITAIRE`, `DATE_SORTIE`, `FOURNISSEUR_ID`, `idrangemennt`, `prix_vente`, `tva`, `qtite_min`, `reference`, `deleted`) VALUES
(5, 1, 'hjhhj', '44', '2019-05-05 06:17:20', '2019-04-21 05:44:29', NULL, NULL, '122', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(7, 1, 'hjhhj', '1', '2019-04-21 05:47:08', '2019-04-21 05:47:08', NULL, NULL, '122', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(8, 1, 'hjhhj', '1', '2019-04-21 05:47:27', '2019-04-21 05:47:27', NULL, NULL, '122', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(9, 1, 'desi', '704', '2019-05-04 19:48:07', '2019-04-21 06:26:38', NULL, NULL, '10000', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(10, 1, 'designation', '102', '2019-04-21 19:16:37', '2019-04-21 08:35:30', NULL, NULL, '1999', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(11, 4, 'hhfhdh', '15', '2019-04-21 14:27:26', '2019-04-21 09:25:17', NULL, NULL, '10000', NULL, 3, NULL, NULL, NULL, NULL, NULL, 0),
(12, 2, 'ehhhjd', '3', '2019-04-22 10:15:56', '2019-04-21 09:25:38', NULL, NULL, '10000', NULL, 2, NULL, NULL, NULL, NULL, NULL, 0),
(13, 1, 'jjhghj', '111', '2019-04-22 09:58:58', '2019-04-22 09:58:58', NULL, NULL, '2112121', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(14, 1, 'ddjj', '12', '2019-04-27 23:30:26', '2019-04-27 23:30:26', NULL, NULL, '109', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(15, 1, 'jjjkjjk', '111', '2019-04-30 13:17:15', '2019-04-30 13:17:15', NULL, NULL, '201', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(16, 1, 'jjjkjjk', '111', '2019-04-30 13:17:18', '2019-04-30 13:17:18', NULL, NULL, '201', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(17, 1, 'jjjkjjk', '111', '2019-04-30 13:20:33', '2019-04-30 13:20:33', NULL, NULL, '201', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(18, 1, 'sds', '12', '2019-05-04 19:05:44', '2019-05-04 19:05:44', NULL, NULL, '10000', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0),
(19, 1, 'bbnn', '101', '2019-05-04 19:55:49', '2019-05-04 19:55:04', NULL, NULL, '1333', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `ID` int(11) NOT NULL,
  `LIBELLE` varchar(254) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `cupdated_by` int(11) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categories`
--

INSERT INTO `categories` (`ID`, `LIBELLE`, `CREATED_AT`, `UPDATED_AT`, `created_by`, `cupdated_by`, `deleted`) VALUES
(1, 'categorie1wew', NULL, NULL, NULL, NULL, 0),
(2, 'categorie 2', NULL, NULL, NULL, NULL, 0),
(3, 'sdfsdfsd cxvxcxcvxc', '2019-04-21 06:32:44', '2019-04-21 06:32:44', NULL, NULL, 0),
(4, 'sdfsdfsd', '2019-04-21 06:32:49', '2020-04-04 21:16:37', NULL, NULL, 1),
(5, 'categorie 223', '2019-05-05 13:38:12', '2019-05-05 13:38:12', NULL, NULL, 0),
(6, 'categorie 223', '2019-05-05 13:38:26', '2019-05-05 13:38:26', NULL, NULL, 0),
(7, 'qeqweqwe', NULL, NULL, NULL, NULL, 0),
(8, 'jkjkkkkkkkkkkkkkkkkkk', NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idclient` int(11) NOT NULL,
  `nom` varchar(250) DEFAULT NULL,
  `prenom` varchar(250) DEFAULT NULL,
  `tel` varchar(250) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `date_coman` date DEFAULT NULL,
  `montant_t_ht` int(11) DEFAULT NULL,
  `montant_t_ttc` int(11) DEFAULT NULL,
  `idclient` int(11) DEFAULT NULL,
  `tva` tinyint(1) DEFAULT '0',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `employes`
--

CREATE TABLE `employes` (
  `UTILISATEUR_ID` int(11) NOT NULL,
  `ID` decimal(8,0) DEFAULT NULL,
  `NOM` varchar(254) DEFAULT NULL,
  `PRENOM` varchar(254) DEFAULT NULL,
  `adresse` varchar(254) DEFAULT NULL,
  `telephone` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) DEFAULT NULL,
  `sigle` varchar(50) DEFAULT NULL,
  `attribut` varchar(250) DEFAULT NULL,
  `type` varchar(250) DEFAULT NULL,
  `adresse` varchar(250) DEFAULT NULL,
  `telephone` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `date_fact` date DEFAULT NULL,
  `idcommande` int(11) DEFAULT NULL,
  `versement` int(11) DEFAULT NULL,
  `reste_payer` int(11) DEFAULT NULL,
  `montant_ht` int(11) DEFAULT NULL,
  `montant_ttc` int(11) DEFAULT NULL,
  `mode_payement` varchar(250) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `create_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseurs`
--

CREATE TABLE `fournisseurs` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(200) DEFAULT NULL,
  `PRENOM` varchar(200) DEFAULT NULL,
  `ADRESSE` varchar(200) DEFAULT NULL,
  `TELEPHONE` varchar(200) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `created_by` date DEFAULT NULL,
  `updated_by` date DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `fournisseurs`
--

INSERT INTO `fournisseurs` (`ID`, `NOM`, `PRENOM`, `ADRESSE`, `TELEPHONE`, `created_at`, `updated_at`, `created_by`, `updated_by`, `deleted`) VALUES
(1, 'fournisseurs 1', 'fournisseur', 'adresse', 'telephone', NULL, NULL, NULL, NULL, 0),
(2, 'fournisseur 2', 'prenom', 'adresse', '111323', NULL, NULL, NULL, NULL, 0),
(3, 'wweew', 'wqw', 'www', 'wqqwqw', '2019-04-21', '2019-04-21', NULL, NULL, 0),
(5, 'ewwewe', 'wqqwqwq', 'qwwqqw', '12122121', '2019-05-05', '2019-05-05', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `rangement`
--

CREATE TABLE `rangement` (
  `id` int(11) NOT NULL,
  `libelle` varchar(250) DEFAULT NULL,
  `designation` varchar(250) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `rangement`
--

INSERT INTO `rangement` (`id`, `libelle`, `designation`, `created_at`, `updated_at`, `updated_by`, `created_by`, `deleted`) VALUES
(1, 'nom', 'designation ttt hhhh', '2020-04-04', '2020-04-04', NULL, NULL, 1),
(2, 'test', 'test njjj', '2020-04-04', '2020-04-04', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `ID` int(11) NOT NULL,
  `LIBELLE` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `roles`
--

INSERT INTO `roles` (`ID`, `LIBELLE`) VALUES
(1, 'ADMINISTRATEUR'),
(2, 'EMPLOYE');

-- --------------------------------------------------------

--
-- Structure de la table `taxe`
--

CREATE TABLE `taxe` (
  `id` int(11) NOT NULL,
  `libelle` varchar(250) DEFAULT NULL,
  `valeur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `usagep`
--

CREATE TABLE `usagep` (
  `id` int(11) NOT NULL,
  `libelle` varchar(250) DEFAULT NULL,
  `designation` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `ID` int(11) NOT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `USERNAME` varchar(254) DEFAULT NULL,
  `MOTDEPASSE` varchar(254) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `CREATED_BY` varchar(254) DEFAULT NULL,
  `UPDATED_BY` varchar(254) DEFAULT NULL,
  `NOM` varchar(254) DEFAULT NULL,
  `PRENOM` varchar(254) DEFAULT NULL,
  `ADRESSE` varchar(200) DEFAULT NULL,
  `TELEPHONE` varchar(100) DEFAULT NULL,
  `role` varchar(250) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`ID`, `ROLE_ID`, `USERNAME`, `MOTDEPASSE`, `created_at`, `updated_at`, `CREATED_BY`, `UPDATED_BY`, `NOM`, `PRENOM`, `ADRESSE`, `TELEPHONE`, `role`, `deleted`) VALUES
(1, 1, 'erfewfwqqqqqq', '1234', '2019-04-20 20:44:35', '2019-04-20 20:44:35', NULL, NULL, 'asdasjjjjJJJ', 'QWQWWQ', 'QWQQWQ', 'WWWQ', NULL, 0),
(2, 1, 'erfewfw', '1234', '2019-04-20 20:46:20', '2019-04-20 20:46:20', NULL, NULL, '223', 'wweew', 'wwewe', 'weewww', NULL, 0),
(5, 1, 'erfewfw', '1234', '2019-04-20 20:51:07', '2019-04-20 20:51:07', NULL, NULL, 'sdfsd', 'dfsf', 'fdsfsd', 'sddfs', NULL, 0),
(6, 1, 'dasfs', 'ddsfsdfs', '2019-04-20 21:44:25', '2019-04-20 21:44:25', NULL, NULL, 'sdffdfs', 'sdfsdsd', 'ddsfsfd', 'sdfsdfs', NULL, 0),
(8, 1, 'jjsdfjs', '22222', '2019-04-20 21:47:10', '2019-04-20 21:47:10', NULL, NULL, 'jfjdfjdsfsj', 'jsjdfsdjfjsd', 'jsdjfsjd', 'jjdsfjsdj', NULL, 0),
(9, 1, 'kd', '11111', '2019-04-20 22:10:52', '2019-04-20 22:10:52', NULL, NULL, 'kader', 'kader', 'kader', '2222', NULL, 0),
(10, 1, 'www', '1234', '2019-04-21 05:23:01', '2019-04-21 05:23:01', NULL, NULL, 'dsfsd', 'www', 'www', 'www', NULL, 0),
(11, 1, 'jhhhj', '122', '2019-04-21 23:57:17', '2019-04-21 23:57:17', NULL, NULL, 'ghggh', 'ghhjhj', 'hhjjh', 'hjhjhjjh', NULL, 0),
(12, 2, 'jhhhj', '222', '2019-04-21 23:57:17', '2019-04-21 23:57:17', NULL, NULL, 'ghggh', 'ghhjhj', 'hhjjh', 'hjhjhjjh', NULL, 0),
(13, 2, 'test', 'test', '2019-05-05 19:09:48', '2019-05-05 19:09:48', NULL, NULL, 'test', 'test', 'test', '199999', NULL, 0),
(14, NULL, 'kdr', 'b2d7edd1e671526e66decba802095f4d', '2020-04-02 17:45:05', '2020-04-02 17:45:05', NULL, NULL, 'ZERZERZ', 'zerzer', NULL, NULL, 'Sécrétaire', 0),
(15, NULL, 'qwerty', '81dc9bdb52d04dc20036dbd8313ed055', '2020-04-02 17:56:35', '2020-04-02 17:56:35', NULL, NULL, 'SSFDSDFSD', 'qwerty', NULL, NULL, 'Administrateur', 0),
(16, NULL, '1234', '81dc9bdb52d04dc20036dbd8313ed055', '2020-04-02 18:02:57', '2020-04-02 18:02:57', NULL, NULL, 'JKJJKKJ', 'qwwe', NULL, NULL, 'Sécrétaire', 0),
(17, NULL, 'qwwe', '81dc9bdb52d04dc20036dbd8313ed055', '2020-04-02 18:19:05', '2020-04-02 18:19:05', NULL, NULL, '4EWREW', 'wreqwe', NULL, NULL, 'Sécrétaire', 0),
(18, NULL, 'qwerty', '81dc9bdb52d04dc20036dbd8313ed055', '2020-04-02 18:23:03', '2020-04-02 18:23:03', NULL, NULL, 'FEFERFRE', 'qwerty', NULL, NULL, 'Caissier', 0);

-- --------------------------------------------------------

--
-- Structure de la table `vendres`
--

CREATE TABLE `vendres` (
  `UTILISATEUR_ID` int(11) DEFAULT NULL,
  `ARTICLE_ID` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `QUANTITE` decimal(8,0) DEFAULT NULL,
  `DATE_SORTIE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `vendres`
--

INSERT INTO `vendres` (`UTILISATEUR_ID`, `ARTICLE_ID`, `ID`, `created_at`, `updated_at`, `QUANTITE`, `DATE_SORTIE`) VALUES
(1, 9, 5, '2019-04-21 21:47:10', '2019-04-21 21:47:10', '10', '2019-04-21 21:47:10'),
(1, 9, 6, '2019-04-21 21:47:15', '2019-04-21 21:47:15', '20', '2019-04-21 21:47:15'),
(1, 9, 7, '2019-04-21 21:47:35', '2019-04-21 21:47:35', '20', '2019-04-21 21:47:35'),
(1, 9, 8, '2019-04-21 22:30:47', '2019-04-21 22:30:47', '120', '2019-04-21 22:30:47'),
(1, 9, 9, '2019-04-21 23:10:46', '2019-04-21 23:10:46', '200', '2019-04-21 23:10:46'),
(10, 9, 10, '2019-04-21 23:14:32', '2019-04-21 23:14:32', '1000', '2019-04-21 23:14:32'),
(10, 5, 11, '2019-05-05 06:12:14', '2019-05-05 06:12:14', '90', '2019-05-05 06:12:14'),
(10, 5, 12, '2019-05-05 06:15:32', '2019-05-05 06:15:32', '8', '2019-05-05 06:15:32'),
(10, 5, 13, '2019-05-05 06:17:20', '2019-05-05 06:17:20', '9', '2019-05-05 06:17:20');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_appartients` (`CATEGORIE_ID`),
  ADD KEY `FOURNISSEUR_ID` (`FOURNISSEUR_ID`),
  ADD KEY `rangement_article` (`idrangemennt`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idclient`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `employes`
--
ALTER TABLE `employes`
  ADD PRIMARY KEY (`UTILISATEUR_ID`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_com_fact` (`idcommande`);

--
-- Index pour la table `fournisseurs`
--
ALTER TABLE `fournisseurs`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `rangement`
--
ALTER TABLE `rangement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `taxe`
--
ALTER TABLE `taxe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `usagep`
--
ALTER TABLE `usagep`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_avoir` (`ROLE_ID`);

--
-- Index pour la table `vendres`
--
ALTER TABLE `vendres`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_vendres` (`ARTICLE_ID`),
  ADD KEY `UTILISATEUR_ID` (`UTILISATEUR_ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `articles`
--
ALTER TABLE `articles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idclient` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `fournisseurs`
--
ALTER TABLE `fournisseurs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `rangement`
--
ALTER TABLE `rangement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `taxe`
--
ALTER TABLE `taxe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `usagep`
--
ALTER TABLE `usagep`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `vendres`
--
ALTER TABLE `vendres`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `FK_appartients` FOREIGN KEY (`CATEGORIE_ID`) REFERENCES `categories` (`ID`),
  ADD CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`FOURNISSEUR_ID`) REFERENCES `fournisseurs` (`ID`),
  ADD CONSTRAINT `rangement_article` FOREIGN KEY (`idrangemennt`) REFERENCES `rangement` (`id`);

--
-- Contraintes pour la table `employes`
--
ALTER TABLE `employes`
  ADD CONSTRAINT `FK_Heritage_1` FOREIGN KEY (`UTILISATEUR_ID`) REFERENCES `utilisateurs` (`ID`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `fk_com_fact` FOREIGN KEY (`idcommande`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD CONSTRAINT `FK_avoir` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`);

--
-- Contraintes pour la table `vendres`
--
ALTER TABLE `vendres`
  ADD CONSTRAINT `FK_vendres` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `articles` (`ID`),
  ADD CONSTRAINT `FK_vendres2` FOREIGN KEY (`UTILISATEUR_ID`) REFERENCES `utilisateurs` (`ID`),
  ADD CONSTRAINT `vendres_ibfk_1` FOREIGN KEY (`UTILISATEUR_ID`) REFERENCES `utilisateurs` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
