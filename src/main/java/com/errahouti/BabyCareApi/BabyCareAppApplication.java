package com.errahouti.BabyCareApi;

import com.errahouti.BabyCareApi.controller.auth.AuthService;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionMapper;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.NutritionRepo;
import com.errahouti.BabyCareApi.repository.ReminderRepo;
import com.errahouti.BabyCareApi.repository.UserRepo;
import com.errahouti.BabyCareApi.service.ChildService;
import com.errahouti.BabyCareApi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BabyCareAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(BabyCareAppApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthService service,
			UserRepo userRepo,
			ChildRepo childRepo,
			ChildService childService,
			UserService userService,
			ChildMapper childMapper,
			ReminderRepo reminderRepo,
			NutritionRepo nutritionRepo,
			NutritionMapper nutritionMapper

	) {
		return args -> {
//			Child child1 =  new Child();
//
//			child1.setFirstName("samir");
//			child1.setLastName("allaoui");
//			child1.setAge(5);
//			child1.setGender(Gender.MALE);
//			childRepo.save(child1);
//
//			Child child2 =  new Child();
//
//			child2.setFirstName("sara");
//			child2.setLastName("allaoui");
//			child2.setAge(2);
//			child2.setGender(Gender.FEMALE);
//
//			childRepo.save(child2);
//			childRepo.save(child1);
//
//			User user2 = (User) userService.loadUserByUsername("test2@mail.com");
//			userService.addChildren(List.of(child1.getId(),child2.getId()),user2.getId());

//			Child child = childRepo.findById(1L).orElseThrow(NotFoundException::new);
//
////			Reminder reminder = new Reminder();
//			String dateString = "2023-11-11";
//			String timeString = "17:00:00";
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date specificDate = dateFormat.parse(dateString + " " + timeString);
//
////			reminder.setReminderDate(specificDate);
////			reminder.setReminderState(ReminderState.UPCOMING);
//
//			Nutrition nutrition = new Nutrition();
//			nutrition.setNutritionType(NutritionType.SOLID);
//			nutrition.setLabel("third meal of the day");
//			nutrition.setReminderDate(specificDate);
//			nutrition.setReminderState(ReminderState.UPCOMING);
//
////			reminderRepo.save(reminder);
//			nutritionRepo.save(nutrition);
//
//			childService.addNutritionReminder(nutritionMapper.toNutritionDTO(nutrition),
//					childMapper.toChildDTO(child));
//			System.out.println(childRepo.findByIdAndParentId(1L,1L));
			};
	}



}
