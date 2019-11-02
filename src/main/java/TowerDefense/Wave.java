package TowerDefense;

import java.util.ArrayList;
import java.util.Random;

import static TowerDefense.Game.TILE_SIZE;
import static TowerDefense.Game.pause;
import static Util.Clock.Delta;

public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private Enemy[] enemyTypes;
    private ArrayList<Enemy> enemyList;
    private int enemiesPerWave;
    private boolean waveCompleted;

    public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave ) {
        this.spawnTime = spawnTime;
        this.enemyTypes = enemyTypes;
        timeSinceLastSpawn = 0;
        enemyList = new ArrayList<Enemy>();
        this.enemiesPerWave = enemiesPerWave;
        this.waveCompleted = false;
        Spawn();
    }

    public void Update(){
        boolean allEnemiesDead = true;
        if(!pause){
            if(enemyList.size() < enemiesPerWave){
                timeSinceLastSpawn += Delta();
                if(timeSinceLastSpawn >= spawnTime){
                    Spawn();
                    timeSinceLastSpawn = 0;
                }
            }
        }
        for(Enemy e: enemyList){
            if(e.isAlive()) {
                allEnemiesDead = false;
                if(!pause) e.update();
                e.draw();
            }
            //else enemyList.remove(0);
        }
        if(allEnemiesDead) waveCompleted = true;
    }

    public void Spawn(){
        Random r = new Random();
        int index = r.nextInt(4);
        enemyList.add(new Enemy(enemyTypes[index].getTexture(),enemyTypes[index].getStartTile(), enemyTypes[index].getTileGrid(),
                TILE_SIZE,TILE_SIZE, enemyTypes[index].getHealth(), enemyTypes[index].getSpeed(), enemyTypes[index].getReward()));
    }

    public boolean isCompleted(){
        return waveCompleted;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
