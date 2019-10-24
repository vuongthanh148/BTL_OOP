package TowerDefense;

import java.util.ArrayList;
import java.util.Random;

import static TowerDefense.Game.TILE_SIZE;
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
        if(enemyList.size() < enemiesPerWave){
            timeSinceLastSpawn += Delta();
            if(timeSinceLastSpawn > spawnTime){
                Spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for(Enemy e: enemyList){
            if(e.isAlive()) {
                allEnemiesDead = false;
                e.Update();
                e.Draw();
            }
            //else enemyList.remove(0);
        }
        if(allEnemiesDead) waveCompleted = true;
    }

    public void Spawn(){
        int index = 0;
        Random r = new Random();
        index = r.nextInt(3);
        enemyList.add(new Enemy(enemyTypes[index].getTexture(),enemyTypes[index].getStartTile(), enemyTypes[index].getTileGrid(),
                TILE_SIZE,TILE_SIZE, enemyTypes[index].getHealth(), enemyTypes[index].getSpeed()));
    }

    public boolean isCompleted(){
        return waveCompleted;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
