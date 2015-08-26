# --- !Ups

CREATE SCHEMA IF NOT EXISTS `biblia_do_brasileirao` COLLATE latin1_general_ci ;
USE `biblia_do_brasileirao` ;

CREATE TABLE times (
    id bigint  AUTO_INCREMENT PRIMARY KEY,
    teamName varchar(100) collate latin1_general_ci
);

CREATE TABLE historico (
    id bigint  AUTO_INCREMENT PRIMARY KEY,
    homeTeam_id bigint,
    homeResult varchar(2),
    awayResult varchar(2),
    awayTeam_id bigint,
    gameYear varchar(4),
    gameDate varchar(20),
    gameDay varchar(20),
INDEX homeTeam_ind (homeTeam_id),
INDEX awayTeam_ind (awayTeam_id),
FOREIGN KEY (homeTeam_id) REFERENCES biblia_do_brasileirao.times(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (awayTeam_id) REFERENCES biblia_do_brasileirao.times(id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB ;


CREATE TABLE rodadas (
    id bigint  AUTO_INCREMENT PRIMARY KEY,
    homeTeam_id bigint,
    awayTeam_id bigint,
    gameYear varchar(4),
    gameDate dateTime,
    gameDay varchar(20),
    isRodadaAtual boolean,
    INDEX homeTeam_ind (homeTeam_id),
INDEX awayTeam_ind (awayTeam_id),
FOREIGN KEY (homeTeam_id) REFERENCES biblia_do_brasileirao.times(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (awayTeam_id) REFERENCES biblia_do_brasileirao.times(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE = InnoDB;


CREATE TABLE classificacao (
    id bigint  AUTO_INCREMENT PRIMARY KEY,
    posicao int,
    team_id int,
    pontos int,
    jogos int,
    vitorias int,
    empates int,
    derrotas int,
    golspro int,
    golscontra int,
    saldogols int
)ENGINE = InnoDB;





