#분산모바일데이터관리 프로그래밍 과제(JAVA) #1

##1. 과제 목표

- Convoy Query를 Computing하기 위한 알고리즘인 COHERENT MOVING CLUSTER(CMC) 구현 ([1] 참조)
    - Convoy Query
        - Input
            - a set of trajectories of N objects: List(Trajectory) o
            - an integer m: int m
            - an integer lifetime k: int k
            - and a distance threshold e: double e

        - Output
            - All possible groups of objects, 
                - so that each group consists of a (maximal) set of density-connected objects 
                - with respect to e and m during at least k consecutive time points

##2. 최종 목표

- [CMC](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/CMC.java) Class의 method: cm_clustering 구현하기

    ```JAVA
    import java.util.List;

    public class CMC {

        public static List<Convoy> cm_clustering(List<Trajectory> o, int m, int k, double e){

            //TODO: Implement cmc here

            return Convoy_Result;
        }
	```
- [Test_Driver](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/Test_Driver.java) 돌려서 결과 확인하기
	- Input Data
		- a set of trajectories of 20 objects: 4절의 샘플 데이터 이용
		- m = 4;
		- k = 4;
		- e = 5;

	- Output(예시)
    ```
    Start_time: 2.0,	End_Time: 6.0	(5)
    obj_list: 11, 12, 13, 14, 15

    Start_time: 4.0,	End_Time: 9.0	(6)
    obj_list: 1, 2, 3, 4, 5, 6

    Start_time: 8.0,	End_Time: 13.0	(6)
    obj_list: 15, 16, 17, 18, 19

    Start_time: 9.0,	End_Time: 14.0	(6)
    obj_list: 7, 8, 9, 10, 11

    Start_time: 16.0,	End_Time: 22.0	(7)
    obj_list: 11, 12, 13, 14, 15

    Start_time: 19.0,	End_Time: 22.0	(4)
    obj_list: 2, 3, 4, 5, 6, 7

    ```

##3. 구현 범위

### Outline

![code_cmc.png](http://wiki.dataknow.net/img/Dataset.PNG)

### Implemented code

| CLASS| 역할 |
|--------:|---------|
|[STPoint.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/STPoint.java)   |SPatio-Temporal Point를 모델링함 |
|[Trajectory.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/Trajectory.java)|하나의 moving object가 생성한 SPatio-Temporal Point들을 List 형태로 관리함. 이동궤적을 모델링함 |
|[Obj_List.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/Obj_List.java)|하나의 Cluster 또는 하나의 Convoy에 속하는 moving object의 집합 Set을 모델링함. List<Integer>로 구현됨|
|[Cluster.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/Cluster.java)|Point들의 군집(Cluster)을 모델링 함. Obj_List를 상속하여 해당 군집에 속하는 moving object를 Listing함|
|[DBSCAN.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/DBSCAN.java)|DBSCAN[2]을 구현함. Trajectory들의 List, MinPoints, epsilon을 입력받아 Cluster 집합(Cluster[])을 생성해내는 method dbscan_to_cluster를 제공함.|

### Unimplemented code (부분적으로만 구현됨)
| CLASS| 역할 |
|--------:|---------|
|[TrajectoryParser.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/TrajectoryParser.java)   |Text file (4. Sample Dataset 참조) 을 parsing하여 List<Trajectory>를 반환하는 method를 제공해야 함|
|[Convoy.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/Convoy.java)|Trajectory들의 군집(Cluster)인 Convoy를 모델링해야 함. 단, Obj_List를 상속하여 해당 Convoy에 속하는 moving object를 Listing함|
|[CMC.java](https://github.com/ws-choi/CMC_assignment_JAVA/blob/master/src/CMC.java)|본 과제의 최종 목표인 COHERENT MOVING CLUSTER(CMC)이 구현되어 있어야 함. cm_clustering이라는 이름으로 CMC를 구현함 |



##4. Sample Dataset

- 데이터 다운로드: [dataset.csv](https://www.dropbox.com/s/pw8fvmmetajvqnl/dataset.csv?dl=0), [dataset.txt](https://www.dropbox.com/s/hjfa2sc7wn8ryz9/dataset.txt?dl=0) 
	- Moving Object가 1초부터 20초동안 움직인 좌표를 매초 기록한 궤적 데이터
	- 가시화 된 데이터 셋

![Dataset.PNG](http://wiki.dataknow.net/img/ws_code_cmc.png)



- 데이터 형식: [CSV(Comma-separated values)]( https://en.wikipedia.org/wiki/Comma-separated_values )
	- Column 설명
		- obj_id: moving object의 아이디
		- y		: 좌표가 기록된 시각
		- x		: t에서의 x좌표
		- y		: t에서의 y좌표

- 예시
    - 아래 csv에 대한 해석
        - obj_id 가 1인 moving object가 1초부터 3초동안 (0,0)->(1,1)->(2,2)로 이동
        - obj_id 가 2인 moving object가 1초부터 3초동안 (4,4)->(5,5)->(7,7)로 이동
        -

    ```csv
    obj_id,t,x,y
    1, 1, 0, 0
    1, 2, 1, 1
    1, 3, 2, 2
    2, 1, 4, 4
    2, 2, 5, 5
    2, 3, 7, 7
    ```


- cm_clustering 구현 후 Test Class의 main을 실행했을 대의 결과(예시) (#3의 Sample Dataset 사용 시)

    ```
    Start_time: 2.0,	End_Time: 6.0	(5)
    obj_list: 11, 12, 13, 14, 15

    Start_time: 4.0,	End_Time: 9.0	(6)
    obj_list: 1, 2, 3, 4, 5, 6

    Start_time: 8.0,	End_Time: 13.0	(6)
    obj_list: 15, 16, 17, 18, 19

    Start_time: 9.0,	End_Time: 14.0	(6)
    obj_list: 7, 8, 9, 10, 11

    Start_time: 16.0,	End_Time: 22.0	(7)
    obj_list: 11, 12, 13, 14, 15

    Start_time: 19.0,	End_Time: 22.0	(4)
    obj_list: 2, 3, 4, 5, 6, 7

    ```


##5. Download Source Code

- Zip file:  [Downlaod ZIP](https://github.com/ws-choi/CMC_assignment_JAVA/archive/master.zip)
- Git: https://github.com/ws-choi/CMC_assignment_JAVA.git
- Githup: [Github link](https://github.com/ws-choi/CMC_assignment_JAVA)
##참조 문헌
[1] Jeung, Hoyoung, et al. "Discovery of convoys in trajectory databases." Proceedings of the VLDB Endowment 1.1 (2008): 1068-1080.
[2] Ester, Martin, et al. "A density-based algorithm for discovering clusters in large spatial databases with noise." Kdd. Vol. 96. No. 34. 1996.
