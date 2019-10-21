package TowerDefense;

public class WaveManager {
    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Enemy enemyType;
    private Wave currentWave;

    public WaveManager(Enemy enemyType, float timeBetweenEnemies, int enemiesPerWave ) {
        this.enemiesPerWave = enemiesPerWave;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemyType = enemyType;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;

        this.currentWave = null;
    }

    private void Update(){

    }

    private void newWave(){
        currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
    }

}
