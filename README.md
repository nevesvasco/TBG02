## POO_GX - Rush Dash

> Include here one or two paragraphs explaining the main idea of the project, followed by a sentence identifying who the authors are.

O jogo vai ser do género plataforma 2D, em que o personagem vai ser uma figura geométrica em que anda para a frente sozinho ao ritmo da música em 8 bit e o jogador só usa 2 teclas. Uma para saltar, e outra para usar um boost que apanha no nível. 
Há medida que a figura vai avançando, vai ganhando pontos num score que aparece na parte superior do ecrã.
O jogo vai estar dividido por níveis, e há medida que se vai passando os níveis, mais difícil o jogo se torna.
O nível também vai-se tornando mais difícil enquanto o personagem mais avança. Vai aparecendo cada vez mais obstáculos e o ritmo da música vai ficando mais rápido.

Este projeto foi desenvolvido por Betânia dos Santos (a042807@umaia.pt), Daniel Nunes (a042051@umaia.pt) e Vasco Neves (a043335@umaia.pt) para a cadeira de POO 2023/2024.

## Features Planeadas e Implementadas

- **Salto e duplo salto** O personagem vai poder dar um salto e um duplo salto para passar obstáculos normais e mais altos que lhe apareçam pelo caminho.
- **Usar boosts** o personagem vai apanhar boosts que permite usar e diminuir a velocidade do personagem durante um certo período de tempo para ficar mais fácil de ultrapassar os obstáculos.


## Implementação

Inserir as features e o path onde eles estão localizados.

##Mocks##
![Opening Menu](https://github.com/nevesvasco/TBG02/assets/131387962/16495469-5874-4e3e-aa24-a30153ec0f14)
![Pause menu](https://github.com/nevesvasco/TBG02/assets/131387962/27a68850-3cbb-4154-8972-db49355ef277)
![Top score menu](https://github.com/nevesvasco/TBG02/assets/131387962/1b92b08c-be84-4f5c-8dd8-d06dbccda2f1)
![mock_ecra_jogo](https://github.com/nevesvasco/TBG02/assets/131387962/d44a6423-8432-427f-8a1c-d0ed87e87e87)
![CONTROLS](https://github.com/nevesvasco/TBG02/assets/131387962/a3b39d4b-276f-4858-b74c-eb9a1ed8912c)

## Consequencias

The use of the State Pattern in the current design allows the following benefits:

- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

## KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

Iremos inserir known code smells caso encontremos algum.

## Padrão

## DATA CLASS

The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].

A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.

## TESTING

- Iremos inserir imagens quando tivermos dados disponiveis.

## Auto Avaliação

- Daniel Nunes: %
- Vasco Neves: %
- Betania Santos: %
