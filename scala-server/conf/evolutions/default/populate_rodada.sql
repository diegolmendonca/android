	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'FLAMENGO'),
	awayTeam_id = (select id from times where teamName = 'GOIÁS'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 18:30:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'FLUMINENSE'),
	awayTeam_id = (select id from times where teamName = 'FIGUEIRENSE'), 
	gameYear  = '2014',
	gameDate =  '2014-04-19 18:30:00',
	gameDay  = 'SÁBADO',
	isRodadaAtual = true;

	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'SÃO PAULO'),
	awayTeam_id = (select id from times where teamName = 'BOTAFOGO'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 16:00:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;

	
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'SANTOS'),
	awayTeam_id = (select id from times where teamName = 'SPORT'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 18:30:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;
	
	
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'ATLÉTICO-PR'),
	awayTeam_id = (select id from times where teamName = 'GRÊMIO'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 16:00:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;
	
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'ATLÉTICO-MG'),
	awayTeam_id = (select id from times where teamName = 'CORINTHIANS'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 16:00:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;
	
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'BAHIA'),
	awayTeam_id = (select id from times where teamName = 'CRUZEIRO'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 16:00:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;
	
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'INTERNACIONAL'),
	awayTeam_id = (select id from times where teamName = 'VITÓRIA-BA'), 
	gameYear  = '2014',
	gameDate =  '2014-04-19 18:30:00',
	gameDay  = 'SÁBADO',
	isRodadaAtual = true;

	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'CRICIÚMA'),
	awayTeam_id = (select id from times where teamName = 'PALMEIRAS'), 
	gameYear  = '2014',
	gameDate =  '2014-04-20 18:30:00',
	gameDay  = 'DOMINGO',
	isRodadaAtual = true;
	
	
	INSERT INTO rodadas SET 
	homeTeam_id  = (select id from times where teamName = 'CHAPECOENSE'),
	awayTeam_id = (select id from times where teamName = 'CORITIBA'), 
	gameYear  = '2014',
	gameDate =  '2014-04-19 21:00:00',
	gameDay  = 'SÁBADO',
	isRodadaAtual = true;