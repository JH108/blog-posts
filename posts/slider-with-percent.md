# Why I like Jetpack Compose more than React Native: A Small Example

Hello 👋 I'm Jesse, I've been building apps across web and mobile for a while now. I got started with Mobile back in 2018 using React Native, and after building a few projects and a few Native modules (before turbo modules was a thing), I was able to pick up Jetpack Compose as it entered Alpha in 2020. We had a greenfield project that I was leading, and decided as a team that going all-in on Compose was the best call for us since our organization had a large and skilled iOS team. 

At the end of the day, I acknowledge that both technologies have a place and that every framework and language comes with tradeoffs. Lately, I've been working a contract building a React Native application with Expo and I've seriously missed working with Jetpack Compose and Kotlin. There's much to be said on the topic, but I had a feature to build that encapsulated one part of why I personally prefer Jetpack Compose for Android development over React Native. 

I needed to add a simple slider to a form that would step from 1 to 100 and update a read-only input field with the value as a percentage. The two examples below detail a little more on the effort that went into each solution. TLDR: Jetpack Compose makes it super simple to build a UI that the user expects, and the ability to cmd+click straight into the source code when figuring out how to use a Composable from the Material library is SO much nicer than trying to dig through node modules… 

## Jetpack Compose

Code: [GitHub]()
 
Thoughts: I only spent about 30 minutes on this version, mostly because I tried to build my own slider first (the animation APIs are so nice) before using the built-in one and then reading the source to figure out how to clean up the track and change the Thumb shape. This was nearly the ideal UI experience with a smooth slider and instant updates to the input field. 

GIF

## React Native

Code: [GitHub]()

Thoughts: I spent around 8x the time trying to get this implementation to look and feel at least close to how the Compose one does. For both implementations, I’ve got a local state variable that updates based on the slider’s change, and a computed variable memoized based on the first state value. I ended up digging into several GitHub issues and going down various rabbit trails in an attempt to have the slider behave like the onSlidingComplete version, and the input like the onValueChange version. 

## Closing 

It isn’t my intention here to say that you should never choose to use React Native or to provide a fully nuanced discussion of React Native vs Jetpack Compose (although that would be highly entertaining). I was simply wondering why these last several months I’ve felt more frustrated with the development process than when I was building purely with Jetpack Compose, and this was one simple example where the developer and user experience was just better with Jetpack Compose. I know that this example can be done in React Native, but my team didn’t have the bandwidth to reach for a library like Reanimated, which I’m sure would’ve given us the control we needed over the UI. But at the end of the day, it’s nice to have smooth UI experiences without needing to constantly reach for other libraries and learn how they work. 

I plan to continue my journey towards crafting delightful software products and love working with anything Mobile. I hope this can serve as an interesting perspective on why a normal person has a preference for which Mobile technology to use. 

Thank you for reading! 
