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

### RoomManager

- gets a hero instance at construction.
- manages current room, next room, etc.
- parses files to rooms

### Room

- initializes with floors
- sets hero
- sets tiles


### Questions

What class is responsible for movement collision?
Makes sense that the room knows what elements it has so the room can say if the object is walkable. 

I already can get the element that is at desired position, so the base of the collision logic is done. 
getPosition - 

The room defines if the is movable!

I will have an enum of movables

Hierarchy of classes? Does it make sense to add more methods? 
If there are methods unique to all that I want them to share.

Enemy: 
They move randomly and attack.
- BadGuy
- Bat
- Thief
- Skeleton

Item
- Sword
- Hammer
- Trap

Power
- Fire
- FireOld

obstacle:
- Wall
- DoorClosed

Environment:
- StairsUp
- StairsDown
- Grass
- Floor
- DoorOpen
- DoorWay

UI:
- Red
- Green
- Black


How does the hero keeps state between rooms?
Makes sense to make the room manager receive the hero - it chooses the current room and the current room adds the hero to a certain position.

Create other classes
Methods need validation!!!

1 - ~~Abstract class GameObject implements ImageTile~~

On hero: if tile is not null -> interact
if is moveable -> setposition(newposition)

interact() - if enemy take damage, else collect item

2 - enemy, obstacle, item,

3- enemy : if isnearhero(position, radius) movetowards(position) else moverandom

4- hero: collectItem if < max inventory & applyeffect -> switch

5- gamemanager this.score, getscore, updatescore, move, defeatenemy, collectitem
gui, hero, roommanager, score,

public void initGame() {
try {
roomManager.getCurrentRoom().addHero(hero);
gui.setEngine(new Engine(this));
gui.newImages(roomManager.getCurrentRoom().getTiles());
gui.go();
gui.setStatus("O jogo começou!");
} catch (Exception e) {
System.err.println("Error during game initialization: " + e.getMessage());
e.printStackTrace();
}
}

6- engine gamemanager gui , notify (gui update),

7-room parser, if line starts with # parsespecial
room.addDoorConfiguration(doorIndex, doorType, targetRoomFile, targetDoorIndex);
8 - roommanager transition to door -> if room file name, getdoorposition, hero setposition

9-  hero interact, if door is not locked or haskey -> transition to room

10- key, haskeyid

11- door - target room, target door, islocked, unlock

### TODO
- ~~add statusmanager and updates to pick objects~~
- add properties on hero health, attack, etc
- ~~add fireball~~
- add enemies
- think about game state score, saving state, loading state,
- think about leaderboard
- ~~restructure status~~



**not happy** 
- same method on doorway and dooropen
- Interface Object and Object structure
- key per room
- key on inventory???
- review objects
- enemy move implementation

SOLVE:
- ~~enemies disappear~~
- prevent multiple enemies from pile: create set with reserved positions.
if reserved containes new position -> setposition. At the end clearall reservations.
- ~~thief: steal: hero.gettatus.getinventory.clear~~
- ~~diagonal: add enums direction: upleft, upright etc~~
- ~~add set trap method on hero. then react trap to activate trap on enemy~~
- ~~enemy thread, use room to initiate~~
- score: defeat enemy, collect item, moves, update score
- Save Method: Writes the hero's position, health, inventory, current room index, enemies' positions, and score to a file.
- Load Method: Reads the saved data and restores the game state by setting the hero's position, health, inventory, current room, enemies, and score.
- leaderboard: savescore, loadscore, addscore, getscore
- outside source have data/saves/memory folder that saves leaderboard savegame.

!!fireball on doors
!!enemies on doors
!!room initiation vs setcurrentroom -> refactor

~~enemies are not recognized by trap.!!~~

- what to save?
- hero position, health, inventory
- enemies health and position
- score
- objects
- save all rooms data?

status messages, menus, 
change game icons

create maps, narrative
add sounds


public void handleGameOver() {
System.out.println("Game Over! You have been defeated.");
roomManager.getCurrentRoom().stopEnemies();
GUI.showMessage("GO", "Game Over! Press R to restart or Q to quit.");

    // Prompt for user name
    String playerName = GUI.promptForInput("Enter your name to save your score:");

    // Save the score
    Leaderboard leaderboard = new Leaderboard();
    leaderboard.addScore(playerName, gameManager.getScore());

    // Wait for user input to restart or quit
    while (true) {
        int keyPressed = GUI.getKeyPressed();
        if (keyPressed == KeyEvent.VK_R) {
            gameManager.startGame();
            break;
        } else if (keyPressed == KeyEvent.VK_Q) {
            System.exit(0);
        }
    }
}