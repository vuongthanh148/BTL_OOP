package TowerDefense;

import java.util.ArrayList;

import static Util.Clock.Delta;

public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;
    private int enemiesPerWave;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave ) {
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        timeSinceLastSpawn = 0;
        enemyList = new ArrayList<Enemy>();
        this.enemiesPerWave = enemiesPerWave;
        Spawn();
    }

    public void Update(){
        timeSinceLastSpawn += Delta();
        if(timeSinceLastSpawn > spawnTime){
            Spawn();
            timeSinceLastSpawn = 0;
        }

        for(Enemy e: enemyList){
            if(e.isAlive()) {
                e.Update();
                e.Draw();
            }
        }
    }

    public void Spawn(){
        enemyList.add(new Enemy(enemyType.getTexture(),enemyType.getStartTile(), enemyType.getTileGrid(),
                64,64, enemyType.getHealth(), enemyType.getSpeed()));
    }
}
