package TowerDefense;

import java.util.ArrayList;

public class WaveManager {
    private float timeSinceLastWave, timeBetweenEnemies;
    public static int waveNumber, enemiesPerWave;
    private Enemy[] enemyTypes;
    private Wave currentWave;

    public WaveManager(Enemy[] enemyTypes, float timeBetweenEnemies, int enemiesPerWave ) {
        this.enemiesPerWave = enemiesPerWave;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemyTypes = enemyTypes;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;
        this.currentWave = null;
        newWave();
    }

    public void update(){
        if(!currentWave.isCompleted()) currentWave.Update();
        else newWave();
    }

    private void newWave(){
        currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave: " + waveNumber);
    }

    public Wave getCurrentWave() {
        return currentWave;
    }
    public ArrayList<Enemy> getListEnemy(){
        ArrayList<Enemy> listEnemy = new ArrayList<Enemy>();
        for(Enemy e:enemyTypes){
            listEnemy.add(e);
        }
        return listEnemy;
    }
}
