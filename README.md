## POO_GX - Rush Dash

> Include here one or two paragraphs explaining the main idea of the project, followed by a sentence identifying who the authors are.

O jogo vai ser do género plataforma 2D, em que o personagem vai ser uma figura geométrica em que anda para a frente sozinho ao ritmo da música em 8 bit e o jogador só usa 2 teclas. Uma para saltar, e outra para usar um boost que apanha no nível. 
Há medida que a figura vai avançando, vai ganhando pontos num score que aparece na parte superior do ecrã.
O jogo vai estar dividido por níveis, e há medida que se vai passando os níveis, mais difícil o jogo se torna.
O nível também vai-se tornando mais difícil enquanto o personagem mais avança. Vai aparecendo cada vez mais obstáculos e o ritmo da música vai ficando mais rápido.

Este projeto foi desenvolvido por Betânia dos Santos (a042807@umaia.pt), Daniel Nunes (a042051@umaia.pt) e Vasco Neves (a043335@umaia.pt) para a cadeira de POO 2023/2024.

## Como se joga

Space Bar - Saltar

Escape - Menu

V - Boost

## Docs
![Opening Menu and Leaderboard](https://github.com/nevesvasco/TBG02/blob/main/Docs/Main%20Menu.png)
![Pause menu](https://github.com/nevesvasco/TBG02/blob/main/Docs/Ecr%C3%A3%20de%20Pausa.png)
![mock_ecra_jogo](https://github.com/nevesvasco/TBG02/blob/main/Docs/Ecr%C3%A3%20de%20jogo.png)
![Game Over Menu](https://github.com/nevesvasco/TBG02/blob/main/Docs/Game%20Over.png)

## Funções implementadas
O jogo vai ser do género plataforma 2D, em que o personagem vai ser uma figura geométrica em que anda para a frente sozinho ao ritmo da música em 8 bit e o jogador só usa 2 teclas. Uma para saltar, e outra para usar um boost que apanha no nível. 
Há medida que a figura vai avançando, vai ganhando pontos num score que aparece na parte superior do ecrã.
O jogo tem um nível infinito que só termina quando o jogador toca num obstáculo, sendo que o nível de dificuldade é grande desde o início do jogo.

## Funções não implementadas
O jogo vai estar dividido por níveis, e há medida que se vai passando os níveis, mais difícil o jogo se torna.
O nível também vai-se tornando mais difícil enquanto o personagem mais avança. Vai aparecendo cada vez mais obstáculos e o ritmo da música vai ficando mais rápido.

## UML

![UML Final das classes e métodos do jogo](https://github.com/nevesvasco/TBG02/blob/main/Docs/UML%20Final.png)

## Consequências
O jogador quando toca num obstáculo perde o jogo. Aparece de seguida o ecrã de Game Over, podendo voltar a reiniciar a partida ou fechar a sessão. Se a pontuação do jogador estiver entre os 10 melhores, essa pontuação aparece no Leaderboard no Main Menu.
O jogador pode também carregar na tecla V, para ter um boost de aceleração de 2 segundos, ganhando assim mais pontos.

## Testing

Fizemos ao todo, 7 classes de teste para o projeto, ficando com 81% de coverage total.
Os testes que fizemos foram ArenaTest, ObstacleTest, PauseMenuTest, PlayerTest, PlaySoundTest, PositionTest e WallTest.

![Percentagem de coverage do jogo](https://github.com/nevesvasco/TBG02/blob/main/Docs/Percentagem%20de%20Coverage.png)

## Self-Evaluation

O trabalho foi praticamente todo executado em conjunto, mas em cada sessão que nos reuni-mos fazemos num PC diferente para que os três pudessem ter commits suficientes no Github.

Betânia Dos Santos: 33%

Daniel Nunes: 33%

Vasco Neves: 33%
