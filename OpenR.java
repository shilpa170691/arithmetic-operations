package Inheritence;

public class OpenR {
	
	public static void main(String[] args) {
		
	}
	
	
	
	public boolean isListDisplayedCheckByEachElement(List<WebElement> elements) throws Throwable {
		 
        boolean statusOfDisplayed = false;
        String pageSource = "";
        boolean flag = false;
        String lastRoundNameUniqueIdFromFirstSetOfListsIs;
        String currentRoundNameUniqueId = null;

            if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
                    .getParameter("PLATFORM").equalsIgnoreCase("IOS")) {
                for(WebElement eachText:elements){
                    if(eachText.isEnabled()){
                       Reporter.log("the displayed names are:"+eachText.getText()+"<br>");
                        statusOfDisplayed=eachText.isEnabled();
                    }

                }
return statusOfDisplayed;

            } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
                    .getParameter("PLATFORM").equalsIgnoreCase("ANDROID")) {
                String firstPageSource = driver.get().getPageSource();
                String lastElementContentDesc1 = null;

                lastRoundNameUniqueIdFromFirstSetOfListsIs = lastElementContentDescUniqueLocatorToIdentifyLastElement(itemListInRoundsScreen, "content-desc");
                int noOfSectionsInList = 0;
while(!isEndOfPage(pageSource)) {
    String pageSource2 = driver.get().getPageSource();
    scrollToBottom();
    Thread.sleep(2000);//my_rounds_list_cell_title_s3_c1
    Reporter.log("Scrolling to bottom of the screen in the list to get the no of sections in the list" + "<br>");
    lastElementContentDesc1 = lastElementContentDescUniqueLocatorToIdentifyLastElement(itemListInRoundsScreen, "content-desc");
    Reporter.log("the last element content desc in the list is: " + lastElementContentDesc1 + "<br>");

    noOfSectionsInList = Integer.parseInt(matchedPattern("\\d+", lastElementContentDesc1));

    Reporter.log("the no of sections in the list in my rounds is: " + noOfSectionsInList + "<br>");
    if (isEndOfPage(pageSource2)) {
        Reporter.log("coming out of scroll to bottom and collecting no of sections while loop:"+"<br>");
        break;
    }
}

                while (!pageSource.equalsIgnoreCase(firstPageSource)) {
                    String pageSource1 = driver.get().getPageSource();
                    scrollToTop();
//                    if (pageSource.equalsIgnoreCase(firstPageSource)) {
//                        break;
//                    }
                    if (isEndOfPage(pageSource1)) {
                        Reporter.log("coming out of scroll to bottom and collecting no of sections while loop:"+"<br>");
                        break;
                    }
                }

                //scrollToTop();
                Thread.sleep(2000);
                //scrollToTopBasedOnRoundPlans();
                try {
                    while (!isEndOfPage(pageSource)) {
                        pageSource = driver.get().getPageSource();
                        for (int i = 1; i <= noOfSectionsInList; i++) {
                            WebElement element = driver.get().findElement(By.xpath("//android.widget.TextView[@content-desc='open_rounds_list_section_count_s" + i + "']"));


                            String eachSectionCountInString = element.getText().trim();
                            int eachSectionCount = Integer.parseInt(eachSectionCountInString);
                            Reporter.log("each Section Count is:" + eachSectionCount + "<br>");

                            for (int j = 1; j <= eachSectionCount; j++) {
                                WebElement roundName = driver.get().findElement(By.xpath("//android.widget.TextView[@content-desc=\"open_rounds_list_cell_title_s" + i + "_c" + j + "\"]"));


                                currentRoundNameUniqueId = roundName.getAttribute("content-desc");
                                Reporter.log("the current round name and content desc is:" + currentRoundNameUniqueId + roundName.getText() + "<br>");//
//here lastRoundNameUniqueIdFromFirstSetOfListsIs is the element which is in view that element
                                if (!currentRoundNameUniqueId.equals(lastRoundNameUniqueIdFromFirstSetOfListsIs)) {
//                            if (currentRoundNameUniqueId.equals(lastRoundNameUniqueIdFromFirstSetOfListsIs)) {
                                    //  scrollDownWithPointers(0.4, 0.182);
                                    swipeScreenUsingDimensions(0.5, 0.3, 0.5, 0.182);
                                }
                                Thread.sleep(2000);
if(roundName.isDisplayed()){
    statusOfDisplayed= roundName.isDisplayed();
}

                                lastRoundNameUniqueIdFromFirstSetOfListsIs = lastElementContentDescUniqueLocatorToIdentifyLastElement(itemListInRoundsScreen, "content-desc");
                                if (currentRoundNameUniqueId.equals(lastElementContentDesc1)) {
                                    Reporter.log("flag is true" + "<br>");
                                    flag = true;
                                    break;
                                }
                            }
                        }
                        if (flag) {

//                                                while(!pageSource.equalsIgnoreCase(firstPageSource)){   //added now
//                        scrollToTop();   //added now
//                        if(pageSource.equalsIgnoreCase(firstPageSource)){//added now
//                            break;//added now
//                        }//added now
//                    }   //added now
                            Reporter.log("flag is true hence entered this block" + "<br>");
                            break;
                        }
//                        else{
//                            swipeScreenUsingDimensions(0.5, 0.182, 0.5, 0.4);
//                        }
                    }

                } catch (Exception e) {
                    Reporter.log("Catch block:Failed to display the list of elements" +e.getMessage()+ "<br>");

                    scrollToTop();
                    return false;
                }

              // scrollToTop();
            }

        return statusOfDisplayed;
        }
}
