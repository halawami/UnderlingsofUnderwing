#### Hatch ALL unclaimed adjacent Eggs ```underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffectTests```

- Dragons
  - Abyssal Wyrm, Aeon Wyrm, Nebla Wyrm, Undine Wyrm, Steam Wyrm, Sunstone Wyrm, Twilight Wyrm,  Electric Wyrm , Ethereal Wyrm ,Nature Wyrm, Brimstone Wyrm, Void Wyrm, Iceshard Wyvern, Flamewreathed Wyvern, Opalescent Wyrm, Water Wyrm, Sky Wyrm, Blood Wyrm
- [x] This effect should hatch all unclaimed adjacent eggs
	- One adjacent unclaimed egg
		- ```underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffectTests.testHatchOneAdjacentUnclaimedEgg()```
	- Attempt to hatch claimed egg
		- ```underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffectTests.testAttemptToHatchClaimedEgg()```
	- Attempt to hatch different family dragon
		- ```underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffectTests.testAttemptToHatchDifferentFamilyDragon()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffectTests.testToString()```

#### Return all adjacent cards to deck ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentCardsToDeckEffectTests```

- Dragons
  - Tiamat
- [x] This effect shuffles all adjacent cards to deck
	- Apply effect to no handler
		- ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentCardsToDeckEffectTests.testApplyNoHandler()```
	- Apply effect to wild handler
		- ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentCardsToDeckEffectTests.testApplyWildHandler()```
	- Apply effect to normal handler
		- ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentCardsToDeckEffectTests.testApplyNormalHandler()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentCardsToDeckEffectTests.testToString()```

#### Return all adjacent egg's handlers to ready room ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentHandlersEffectTests```

- Dragons
  - TYCHE
- [x] This effect returns all adjacent egg's handlers to the ready room
	- Apply effect to no handler
		- ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentHandlersEffectTests.testApplyNoHandler()```
	- Apply effect to wild handler
		- ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentHandlersEffectTests.testApplyWildHandler()```
	- Apply effect to normal handler
		- ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentHandlersEffectTests.testApplyNormalHandler()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentHandlersEffectTests.testToString()```
  
#### Add 1 Element to ALL adjacent Eggs ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests```

- Dragons
	- Ethereal Whelp, Sky Whelp, Water Whelp, Steam Whelp, Blood Whelp, Harvest Whelp, Arbor Whelp, Electric Whelp, Twilight Whelp, Brimstone Whelp, Undine Whelp, Sunstone Whelp, Storm Whelp, Void Whelp, Nature Whelp
- [x] This effect adds one element (picked randomly)  to all adjacent eggs
	- Apply effect from a list of one element color
		- ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testApplyOneElementColor()```
	- Apply effect from a list of two different element colors
		- ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testApplyTwoDifferentElementColor()```
	- Apply effect from a list of two same element colors
		- ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testApplyTwoSameElementColor()```
	- Apply effect adding to a wild card
		- ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testApplyToWildCard()```
	- Apply effect with playable spaces
	    - ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testAddElementToCardNoPlayableSpace()```
		- ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testAddElementToCardOnePlayableSpace()```
		- ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testAddElementToCardEightPlayableSpaces()```
	- Apply effect with no elements left in bag
	    - ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testAddElementWithNoElementsLeftInBag()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsToAllAdjacentEggsEffectTests.testToString()```

#### Destroying Elements On All Adjacent Eggs ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests```

- Effects
  - Destroy 1 ~color~ Element on ALL adjacent Eggs
    - Dragons
      - Electric Drake
      - Steam Drake
	  - Nature Drake
  - Destroy 1 of each Secondary Element on ALL adjacent Eggs
    - Dragons
	  - Opalescent Drake
  - Destroy ALL ~color~ and ~color~ Elements on ALL adjacent Eggs
    - Dragons
	  - Abyssal Whelp
	  - Nebula Whelp
	  - Aeon Whelp
  - Destroy 1 of each Primary Element on ALL claimed adjacent Eggs
    - Dragons
	  - Chromatic Drake
  - Destroy ALL ~temperature~ Elements on ALL adjacent Eggs
    - Dragons
	  - Flamewreathed Whelp
	  - Iceshard Whelp
- [x] This effect destroys elements on ALL Adjacent Eggs
  - ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests.testDestroyElementOnCardNoDestroyableElements()```
  - ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests.testDestroyElementOnCardOneDestroyableSpace()```
  - ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests.testDestroyElementOnCardEightDestroyableSpace()```
  - ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests.testDestroyAllElementsOnAllAdjacentEggsEffect()```
  - ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests.testDestroyOneElementOnAllAdjacentEggsEffect()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.adjacenteggs.elements.destroy.DestroyElementsOnAllAdjacentEggsEffectTests.testToStringPlace()```

#### Destroy ALL Black and White Elements on Eggs in play ```underlings.card.effect.wild.alleggsinplay.DestroyBlackAndWhiteElementsTests```
- Dragons
  - Artifeles
  
- [x] This effect destroys all black and white elements on all eggs
  - ```underlings.card.effect.wild.alleggsinplay.DestroyBlackAndWhiteElementsTests.effectTest()```
  - ```underlings.card.effect.wild.alleggsinplay.DestroyBlackAndWhiteElementsTests.effectTestMultipleSpaces()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.alleggsinplay.testToString()```