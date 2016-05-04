Narrative:
As a developer
I want to to test the market to "Headphones"
So that I can use selenium IDE

Scenario: check market.yande.ru headphones section

Given browser open the page on yandex.ru
When the transition to the section market
And choose section Electronics
And choose section headphones
And go to advanced search
And sets the specified search criteria
Then page elements should be 10
And to keep the name of the first element in the list
When copy a saved name in the search box
Then found element should have a similar name
And finished test