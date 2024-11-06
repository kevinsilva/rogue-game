# Projecto

## Intro
 Criar um jogo semelhante ao Rogue, com as seguintes **características**: 
 
- Jogo de estratégia e fantasia.
- O herói faz um movimento de cada vez.
- Os inimigos fazem um movimento em resposta (turno).
- Várias salas (níveis) até ao objectivo final.
- Morte implica voltar ao início ou gravação (permitida em certos pontos do jogo).

## Objectivos****

Criar um motor de jogo usando IG (interface gráfica) fornecida. O mesmo permite:

- O herói percorre salas diferentes com adversários diferentes.
- Existem objectos que podem ser apanhados. Estes objectos alteram a forma como o herói interage com adversários. Ex: comida dá vida, chave abre salas, armas causam dano.
- Máximo de 3 objectos apanhados automaticamente quando o heróis passa.
- Não pode ultrapassar paredes ou adversários.
- Mover em direcção ao adversário causa dano e vice-versa.
- Informação deve aparecer aquando dano ou recolha de objectos.
- Os adversários movimentam-se aleatóriamente até entrar num raio (passam a convergir para o herói).
- O adversário thief movimenta-se na diagonal.
- O herói ataca adversários à distância, na mesma linha ou coluna, com bolas de fogo.
- Pontuação final baseada em vários factores. Ex: -1 pt por cada movimento, x pontos por cada adversário, y por cada objecto apanhado.
- Pontuação não pode ser menor que zero.

## Requisitos

Os mapas das salas devem ser lidos.

### Motor de Jogo

- Recebe da IG informações sobre as teclas pressionadas.
- Pode enviar imagens para janela (Hero ou Floor).
- De cada vez que há alteração, chama-se update da IG.
- Interacção entre objectos deve passar o mínimo possível pelo motor de jogo.
- Formato das pontuações é livre.
- Classes não devem ser alteradas.

### Teclas:
- Setas: posição.
- Espaço: disparar.
- Números 1 a 3: largar objectos.

### Obrigatório
- herança.
- classes abstractas.
- implementação da interface.
- listas.
- leitura e escrita de ficheiros.
- excepções.

## Ficheiro das Salas
- Devem ser lidos no momento de inicialização do jogo.
- W - paredes
- 0, 1 , 2 = portas.
- k - chave 
- \# - não fazem parte do mapa (definição portas e chaves). 
- mapa tem 10x10 caracteres 
- "# 0 D room1.txt 1" - configuração porta 0.
- Tipo porta E, passagem sem porta.



## Sugestão
começar por:

- implementar plus e minus
- colocar boneco a mexer
- leitura do ficheiro e criar mapas. Converter para classes na leitura dos mapas.
- Lista de mapas.
- Classe de salas. 
- Engine recebe lista de classe das salas.
- objectos guardam instancia da position.

## IG

- Consiste na ImageMatrixGUI e na ImageTile.
- Permite abrir uma janela com três áreas: Barra de estado, Janela de Jogo e Barra informativa.
- **Janela de jogo**: 480x480px -> 10x10 posições -> imagens 48x48
- **Barra de estado**: 480x48 -> 10 imagens de 48x48
- **Barra de Info**: apresenta uma linha de info.
- *ImageTile* selecciona uma imagem e indica a posição na matriz onde irá inserir.
- É obrigatório indicar o nome da imagem(Sem extensão) e a posição na grelha.

newImages(List<ImageTile>) -> janela jogo 
e newStatusImages(List<ImageTile>) -> janela status.
 

### Não alterar:
imagematrixui
imagetile

## Bolas de Fogo

- FireTile: setPosition() e validateImpact(), chamados cada vez que a bola se move.
- Método confirma se a bola de fogo atingiu objecto e explode.
- Responsável por tirar vida.
- FireBallThread() recebe direcção e FireTile -> mudar a posição a cada 300ms. Cada vez que move, validateImpact() é chamado. 
- Se o método ValidateImpact() devolve true, então processo continua. Caso false, o ciclo termina e a bola é eliminada.

## Outros
direcções, vectores (convertidos a partir das direccoes)
posição é a coordenada

- ImageMatrixGui: getInstance(), access instance.
- setEngine(): sete engine that will feed GUI.
- setStatus(): display status text.
- go(): launch game window.
- newImages(list ImageTile): adds new set of images
- removeImage(ImageTile): removes given image.
- addImage(ImageTile): adds image to main.
- clearImages(): clear all.
- showMessage(): displays window to user.
- showInputDialog(): shows question returns answer.
- update(): forces new window paint.

## Development

Class Room
Class RoomManager -> creates list of rooms
