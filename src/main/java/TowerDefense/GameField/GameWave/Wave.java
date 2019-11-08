package TowerDefense.GameField.GameWave;

import TowerDefense.GameField.GameEntity.Enemy.Enemy;

import java.util.ArrayList;
import java.util.Random;

import static TowerDefense.GameStage.Game.*;
import static TowerDefense.GamePlay.Player.stageNumber;
import static TowerDefense.GameStage.StateManager.game;
import static Util.Timer.Delta;

public class Wave{
    private float timeSinceLastSpawn, spawnTime;
    private Enemy[] enemyTypes;
    private ArrayList<Enemy> enemyList;
    private int enemiesPerWave, spawnedEnemy;
    private boolean waveCompleted, allEnemyDie;

    public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave ) {
        this.spawnTime = spawnTime / gameSpeed;
        this.enemyTypes = enemyTypes;
        this.spawnedEnemy = 0;
        timeSinceLastSpawn = 0;
        enemyList = new ArrayList<Enemy>();
        this.enemiesPerWave = enemiesPerWave;
        this.waveCompleted = false;
        //Spawn();
    }

    public void Update(){
        allEnemyDie = true;
        if(!pause){
            if(spawnedEnemy < enemiesPerWave){
                timeSinceLastSpawn += Delta();
                if(timeSinceLastSpawn >= spawnTime){
                    Spawn();
                    timeSinceLastSpawn = 0;
                }
            }
        }
        for(int i=0;i< enemyList.size();i++){
            if(enemyList.get(i).isAlive()) {
                allEnemyDie = false;
                if(!pause) enemyList.get(i).update();
                enemyList.get(i).draw();
            }
            else enemyList.remove(i);
        }
        if(allEnemyDie && spawnedEnemy == enemiesPerWave) {
            waveCompleted = true;
            pause = true;
        }
    }

    public void Spawn(){
        Random r = new Random();
        int index = r.nextInt(4);
        enemyList.add(new Enemy(enemyTypes[index].getTexture(),enemyTypes[index].getStartTile(), game.grid,
                TILE_SIZE,TILE_SIZE, enemyTypes[index].getHealth() + (stageNumber-1)*(stageNumber-1)*100, enemyTypes[index].getSpeed() + (stageNumber-1)*40, enemyTypes[index].getReward()));
        this.spawnedEnemy++;
    }

    public boolean isCompleted(){
        return waveCompleted;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
