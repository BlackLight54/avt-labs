# HW1 ‐ Test generation

## 1st task: Getting graphwalker running
![[Graphwalker_running_screenshot.png|600]]
## 2nd task: Windshield wiper
### Final model
![[final_model_Screenshot.png|400]]
### Final test code
![[final_test_codee_Screenshot 2023-10-08 205703.png]]
### Test output
![[graphwaler_final_output_Screenshot 2023-10-08 205747.png]]
### Uncovered problems 
The test uncovered a problem with the implementation, specifically that the AUTO setting doesn't check the data from the rain sensor when it is initially set, but just defaults to 0.5, and only changes when the raindata is updated. This violates the the spec. 
