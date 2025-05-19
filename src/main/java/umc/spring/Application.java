package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;          // ★ 추가
import org.springframework.data.domain.Pageable;         // ★ 추가
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.mission.MissionQueryService;   // ★ 추가
import umc.spring.web.dto.UserMissionSummaryDto;             // ★ 추가

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService    storeService   = context.getBean(StoreQueryService.class);
			MissionQueryService  missionService = context.getBean(MissionQueryService.class);   // ★ 추가

			/* ---------- 기존 Store 테스트 ---------- */
			String name  = "요아정";
			Float  score = 4.0f;

			System.out.println("\n=== STORE 조회 테스트 ===");
			System.out.printf("Name: %s | Score ≥ %.1f%n", name, score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);

			/* ---------- 내 미션 조회 테스트 ---------- */
			Long userId         = 101L;               // ★ 더미 데이터에 맞춰 조정
			Pageable pageable   = PageRequest.of(0, 10);

			System.out.println("\n=== USER MISSION 조회 테스트 ===");

			missionService.getMyMissions(userId, pageable)
					.map(UserMissionSummaryDto::toString)   // toString() 구현돼 있지 않다면 직접 필드 출력
					.forEach(System.out::println);
		};
	}}